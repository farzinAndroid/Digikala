package com.farzin.newdigikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider

import androidx.compose.material.Icon

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.farzin.newdigikala.viewmodel.AddressViewModel
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.address.UserAddress
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.ui.components.OurLoading
import com.farzin.newdigikala.ui.theme.LightCyan
import com.farzin.newdigikala.ui.theme.extraSmall
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun CartAddressSection(
    navController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel()
){


    var addressList by remember {
        mutableStateOf<List<UserAddress>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }


    var address = stringResource(id = R.string.no_address)
    var addressName = ""
    var addressBtnText = stringResource(id = R.string.add_address)

    val addressListResult by viewModel.userAddressList.collectAsState()
    when (addressListResult) {
        is NetworkResult.Success -> {
            addressList = addressListResult.data ?: emptyList()
            if(addressList.isNotEmpty()){
                address = addressList[0].address
                addressBtnText = stringResource(id = R.string.change_address)
                addressName = addressList[0].name
            }
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "CartAddressSection error : ${addressListResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }


    if (loading) {
        OurLoading(100.dp, true)
    }else{
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {

            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
                    .weight(0.15f)
                    .align(Alignment.CenterVertically),
            )

            Column(
                modifier = Modifier
                    .weight(0.85f)
                    .padding(vertical = MaterialTheme.spacing.medium),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ){

                Text(
                    text = stringResource(id = R.string.send_to),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray,
                )

                Text(
                    text = address,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3
                )

                Text(
                    text = addressName,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )

            }


        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    .clickable {

                    },
                text = addressBtnText,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.extraSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.LightCyan,
            )

            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "",
                tint = MaterialTheme.colors.LightCyan,
                modifier = Modifier
                    .size(12.dp)
                    .align(Alignment.CenterVertically)
            )

        }
    }


    Divider(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

}