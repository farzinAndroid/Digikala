package com.farzin.newdigikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import com.farzin.newdigikala.R
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.newdigikala.data.model.home.AmazingItem
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.ui.theme.DigikalaLightGreen
import com.farzin.newdigikala.ui.theme.DigikalaLightRed
import com.farzin.newdigikala.viewmodel.HomeViewModel

@Composable
fun SuperMarketOfferSection(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    var superMarketItemList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val superMarketItemResult by viewModel.superMarketItems.collectAsState()
    when (superMarketItemResult) {
        is NetworkResult.Success -> {
            superMarketItemList = superMarketItemResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("TAG", "superMarketOfferSection error : ${superMarketItemResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.DigikalaLightRed)
    ) {

        LazyRow(modifier = Modifier.background(MaterialTheme.colors.DigikalaLightGreen)) {

            item {
                AmazingOfferCard(R.drawable.supermarketamazings, R.drawable.fresh)
            }

            items(superMarketItemList) { item ->
                AmazingItem(
                    item = item,
                    navController = navController
                )
            }

            item {
                AmazingShowMoreItem()
            }


        }

    }

}