package com.farzin.newdigikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import com.farzin.newdigikala.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.newdigikala.data.model.home.AmazingItem
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.ui.theme.DigikalaLightRed
import com.farzin.newdigikala.viewmodel.HomeViewModel

@Composable
fun AmazingOfferSection(
    viewModel: HomeViewModel = hiltViewModel()
) {

    var amazingItemList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val amazingItemResult by viewModel.amazingItems.collectAsState()
    when (amazingItemResult) {
        is NetworkResult.Success -> {
            amazingItemList = amazingItemResult.data ?: emptyList()
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "AmazingOfferSection error : ${amazingItemResult.message}")
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

        LazyRow(modifier = Modifier.background(MaterialTheme.colors.DigikalaLightRed)) {

            item {
                AmazingOfferCard(R.drawable.amazings, R.drawable.box)
            }

            items(amazingItemList) { item ->
                AmazingItem(item = item)
            }

            item {
                AmazingShowMoreItem()
            }


        }

    }

}