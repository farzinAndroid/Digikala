package com.farzin.newdigikala.ui.screens.address

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.address.UserAddress
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.components.OurLoading
import com.farzin.newdigikala.ui.screens.basket.DetailRow
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.digikalaRed
import com.farzin.newdigikala.ui.theme.grayAlpha
import com.farzin.newdigikala.ui.theme.searchBarBg
import com.farzin.newdigikala.ui.theme.selectedBottomBar
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.Constants
import com.farzin.newdigikala.viewmodel.AddressViewModel
import com.farzin.newdigikala.viewmodel.DataStoreViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowAddressScreen(
    navController: NavHostController,
    isFromBasket: Int,
    addressViewModel: AddressViewModel = hiltViewModel(),
    dataStore: DataStoreViewModel = hiltViewModel()
) {

    var addressList by remember {
        mutableStateOf<List<UserAddress>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(true) {
        addressViewModel.getUserAddressList(Constants.USER_TOKEN)
    }


    val addressListResult by addressViewModel.userAddressList.collectAsState()

    when (addressListResult) {
        is NetworkResult.Success -> {
            addressList = addressListResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("TAG", "addressListSection error : ${addressListResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }



    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddAddressScreen.route)
                },
                backgroundColor = MaterialTheme.colors.digikalaRed
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    ) {
        Column {
            ShowAddressHeader(navController)

            if (loading) {
                val config = LocalConfiguration.current
                OurLoading(config.screenHeightDp.dp, true)
            } else {
                LazyColumn(Modifier.fillMaxSize()) {
                    itemsIndexed(addressList) { index, item ->
                        AddressCard(item, isFromBasket, index, dataStore, navController)
                    }
                }
            }

        }
    }

}

@Composable
private fun AddressCard(
    address: UserAddress,
    isFromBasket: Int,
    itemIndex: Int,
    dataStore: DataStoreViewModel,
    navController: NavHostController,
) {
    Column(
        modifier = Modifier.padding(
            horizontal = MaterialTheme.spacing.biggerSmall,
        )
    ) {

        Row {
            if (isFromBasket >= 0) {
                Column(
                    modifier = Modifier.weight(0.1f)
                ) {
                    RadioButton(
                        selected = isFromBasket == itemIndex,
                        onClick = {
                            dataStore.saveUserAddressIndex(itemIndex.toString())
                            navController.popBackStack()
                        }
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = address.address,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.darkText,
                    maxLines = 2,
                    modifier = Modifier
                        .padding(
                            top = MaterialTheme.spacing.biggerSmall,
                            bottom = MaterialTheme.spacing.biggerMedium
                        )
                )

                DetailRow(
                    painterResource(id = R.drawable.letter),
                    text = address.postalCode,
                    color = MaterialTheme.colors.darkText,
                    fontStyle = MaterialTheme.typography.h5
                )

                DetailRow(
                    painterResource(id = R.drawable.call),
                    text = address.phone,
                    color = MaterialTheme.colors.darkText,
                    fontStyle = MaterialTheme.typography.h5
                )

                DetailRow(
                    painterResource(id = R.drawable.user_outline),
                    text = address.name,
                    color = MaterialTheme.colors.darkText,
                    fontStyle = MaterialTheme.typography.h5
                )

            }
        }


    }


    Divider(
        modifier = Modifier
            .alpha(0.2f)
            .padding(MaterialTheme.spacing.small),
        color = MaterialTheme.colors.grayAlpha
    )
}

@Composable
private fun ShowAddressHeader(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small)
        ) {
            IconButton(onClick = { navController.popBackStack() }
            ) {
                Icon(
                    Icons.Filled.ArrowForward, contentDescription = "Back",
                    tint = MaterialTheme.colors.selectedBottomBar
                )
            }

            Text(
                text = stringResource(id = R.string.addresses),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.ExtraBold
            )
        }

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.extraSmall)
                .background(MaterialTheme.colors.searchBarBg)
                .fillMaxWidth()
        )

    }
}
