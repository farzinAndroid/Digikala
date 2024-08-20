package com.farzin.newdigikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.farzin.newdigikala.data.model.checkout.OrderDetails
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.screens.basket.BuyProcessContinue
import com.farzin.newdigikala.ui.screens.basket.CartPriceDetailSection
import com.farzin.newdigikala.util.Constants
import com.farzin.newdigikala.viewmodel.BasketViewModel
import com.farzin.newdigikala.viewmodel.CheckoutViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckoutScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel(),
) {


    val coroutineScope = rememberCoroutineScope()
    val cartDetail by basketViewModel.cartDetail.collectAsState()
    val currentCartItems by basketViewModel.ourCurrentCartItems.collectAsState()
    val cartDetails by basketViewModel.cartDetail.collectAsState()

    var shippingCost by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(false) }

    var address by remember { mutableStateOf("") }
    var addressPhone by remember { mutableStateOf("") }
    var addressName by remember { mutableStateOf("") }


    LaunchedEffect(true) {
        if (address.isNotBlank())
            checkoutViewModel.getShippingCost(address)
        else
            checkoutViewModel.getShippingCost("")
    }

    val shippingCostResult by checkoutViewModel.shippingCost.collectAsState()
    when (shippingCostResult) {
        is NetworkResult.Success -> {
            shippingCost = shippingCostResult.data ?: 0
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("TAG", "shippingCostResult Error ${shippingCostResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    var orderId by remember { mutableStateOf("") }

    val setNewOrderResult by checkoutViewModel.orderResponse.collectAsState()
    when (setNewOrderResult) {
        is NetworkResult.Success -> {
            orderId = setNewOrderResult.data ?: ""
            navController.navigate(Screen.ConfirmPurchase.withArgs(orderId , cartDetail.payablePrice + shippingCost))
            Log.e("TAG", orderId)
//            loading = false
        }

        is NetworkResult.Error -> {
//            loading = false
            Log.e("TAG", "setNewOrderResult Error ${setNewOrderResult.message}")
        }

        is NetworkResult.Loading -> {
//            loading = true
        }
    }


    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    ModalBottomSheetLayout(
        sheetContent = {
            DeliveryBottomSheet()
        },
        sheetState = sheetState

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn() {
                item { CheckoutTopBarSection(navController) }
                item {
                    CartAddressSection(navController) { userAddressList ->
                        if (userAddressList.isNotEmpty()) {
                            address = userAddressList[0].address
                            addressPhone = userAddressList[0].phone
                            addressName = userAddressList[0].name
                        }
                    }
                }
                item {
                    CartItemReviewSection(currentCartItems, cartDetails) {
                        coroutineScope.launch {
                            if (sheetState.isVisible)
                                sheetState.hide()
                            else
                                sheetState.show()
                        }
                    }
                }
                item { CartItemInfoSection() }
                item { CartPriceDetailSection(cartDetails, shippingCost) }
            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                BuyProcessContinue(cartDetails.payablePrice, shippingCost) {
                    checkoutViewModel.setNewOrder(
                        OrderDetails(
                            token = Constants.USER_TOKEN,
                            orderUserPhone = addressPhone,
                            orderUserName = addressName,
                            orderTotalPrice = cartDetails.payablePrice + shippingCost,
                            orderTotalDiscount = cartDetails.totalDiscount,
                            orderProducts = currentCartItems,
                            orderAddress = address
                        )
                    )
                }
            }
        }
    }
}