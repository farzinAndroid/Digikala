package com.farzin.newdigikala.ui.screens.address

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.address.AddAddressRequest
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.ui.theme.CursorColor
import com.farzin.newdigikala.ui.theme.DarkCyan
import com.farzin.newdigikala.ui.theme.digikalaRed
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.searchBarBg
import com.farzin.newdigikala.ui.theme.selectedBottomBar
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.Constants
import com.farzin.newdigikala.viewmodel.AddressViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AddAddressScreen(
    navController: NavHostController,
    addressViewModel: AddressViewModel = hiltViewModel()
) {

    var name by remember {
        mutableStateOf("")
    }
    var phone by remember {
        mutableStateOf("")
    }
    var postalCode by remember {
        mutableStateOf("")
    }
    var address by remember {
        mutableStateOf("")
    }
    var loading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        addressViewModel.addNewAddressResponse.collectLatest { addNewAddressResponseResult ->
            when (addNewAddressResponseResult) {
                is NetworkResult.Success -> {
                    navController.popBackStack()
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e(
                        "TAG",
                        "addressListSection error : ${addNewAddressResponseResult.message}"
                    )
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }

    Column {
        AddAddressHeader(navController)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Text(
                text = stringResource(id = R.string.address_details),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Text(
                text = stringResource(id = R.string.firstname),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            TextField(
                value = name,
                onValueChange = { name = it },
                shape = MaterialTheme.roundedShape.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.searchBarBg,
                    focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                    unfocusedIndicatorColor = MaterialTheme.colors.searchBarBg,
                    cursorColor = MaterialTheme.colors.CursorColor,
                )
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                text = stringResource(id = R.string.phone_number),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            TextField(
                value = phone,
                onValueChange = { phone = it },
                shape = MaterialTheme.roundedShape.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.searchBarBg,
                    focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                    unfocusedIndicatorColor = MaterialTheme.colors.searchBarBg,
                    cursorColor = MaterialTheme.colors.CursorColor,
                )
            )


            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                text = stringResource(id = R.string.postal_code),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            TextField(
                value = postalCode,
                onValueChange = { postalCode = it },
                shape = MaterialTheme.roundedShape.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.searchBarBg,
                    focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                    unfocusedIndicatorColor = MaterialTheme.colors.searchBarBg,
                    cursorColor = MaterialTheme.colors.CursorColor,
                )
            )


            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                text = stringResource(id = R.string.address),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            TextField(
                value = address,
                onValueChange = { address = it },
                shape = MaterialTheme.roundedShape.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.searchBarBg,
                    focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                    unfocusedIndicatorColor = MaterialTheme.colors.searchBarBg,
                    cursorColor = MaterialTheme.colors.CursorColor,
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(
                        horizontal = MaterialTheme.spacing.small,
                        vertical = MaterialTheme.spacing.small
                    ),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        addressViewModel.saveUserAddress(
                            AddAddressRequest(
                                token = Constants.USER_TOKEN,
                                name = name,
                                phone = phone,
                                postalCode = postalCode,
                                address = address
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.roundedShape.small,
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.confirm_information),
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }


        }
    }

}


@Composable
private fun AddAddressHeader(navController: NavController) {
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
                text = stringResource(id = R.string.add_address),
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
