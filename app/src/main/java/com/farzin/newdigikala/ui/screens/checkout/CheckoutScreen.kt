package com.farzin.newdigikala.ui.screens.checkout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.farzin.newdigikala.R
import androidx.navigation.NavHostController

@Composable
fun CheckoutScreen(
    navController: NavHostController,
){

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
        LazyColumn() {
            item { CheckoutTopBarSection(navController) }
            item { CartAddressSection(navController) }
        }
    }

}