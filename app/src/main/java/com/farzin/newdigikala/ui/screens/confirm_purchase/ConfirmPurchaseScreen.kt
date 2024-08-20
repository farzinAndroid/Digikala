package com.farzin.newdigikala.ui.screens.confirm_purchase

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.purchase.PaymentRequest
import com.farzin.newdigikala.data.model.purchase.PaymentVerificationRequest
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.theme.digikalaRed
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.Constants
import com.farzin.newdigikala.util.Constants.isFromPurchase
import com.farzin.newdigikala.util.Constants.purchaseOrderId
import com.farzin.newdigikala.util.Constants.purchasePrice
import com.farzin.newdigikala.util.DigitHelper
import com.farzin.newdigikala.viewmodel.BasketViewModel
import com.farzin.newdigikala.viewmodel.CheckoutViewModel
import com.farzin.newdigikala.viewmodel.PurchaseViewModel

@Composable
fun ConfirmPurchaseScreen(
    navController: NavController,
    orderId: String,
    orderPrice: String,
    purchaseViewModel: PurchaseViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel(),
) {

    purchaseOrderId = orderId
    purchasePrice = orderPrice

    val context = LocalContext.current

    var orderState by remember { mutableStateOf(context.getString(R.string.waiting_for_purchase)) }

    val purchaseResult by purchaseViewModel.purchaseResult.collectAsState(null)
    val verifyPurchaseResult by purchaseViewModel.verifyPurchaseResult.collectAsState(null)

    LaunchedEffect(true) {

        if (isFromPurchase) {
            purchaseViewModel.verifyPurchase(
                PaymentVerificationRequest(
                    amount = (orderPrice + "0").toLong(),
                    authority = Uri.parse(Constants.afterPurchaseUrl).getQueryParameter("Authority")
                        .toString(),
                    merchant_id = Constants.ZARRINPAL_MERCHANT_ID
                )
            )
        } else {
            purchaseViewModel.startPurchase(
                PaymentRequest(
                    merchant_id = Constants.ZARRINPAL_MERCHANT_ID,
                    amount = (orderPrice + "0").toLong(),
                    callback_url = "farzin://digikala",
                    description = "خرید تستی از دیجی کالا",
                )
            )
        }

    }

    if (purchaseResult != null) {
        purchaseViewModel.openBrowser(
            url = "https://www.zarinpal.com/pg/StartPay/" + purchaseResult!!.data.authority,
            context = context
        )
    }

    if (verifyPurchaseResult != null) {
        if (verifyPurchaseResult?.data?.message == "Paid"){
            orderState = context.getString(R.string.purchase_is_ok)
            basketViewModel.deleteAllCartItems()

        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                MaterialTheme.spacing.medium,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.final_price),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = DigitHelper.digitByLangAndSeparator(orderPrice),
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_status),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = orderState,
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_code),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = orderId,
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Button(
            onClick = {
                isFromPurchase = false
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            },
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.digikalaRed),
            shape = MaterialTheme.roundedShape.small,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        MaterialTheme.spacing.small,
                    ),
                text = stringResource(id = R.string.return_to_home_page),
                color = MaterialTheme.colors.digikalaRed,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
            )
        }

    }


}
