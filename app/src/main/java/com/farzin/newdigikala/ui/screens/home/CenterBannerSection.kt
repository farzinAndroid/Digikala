package com.farzin.newdigikala.ui.screens.home

import android.util.Log
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.newdigikala.data.model.home.Slider
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.R
import com.farzin.newdigikala.ui.components.CenterBannerItem
import com.farzin.newdigikala.viewmodel.HomeViewModel

@Composable
fun CenterBannerSection(
    bannerNumber: Int,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var centerBannerList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val centerBannerResult by viewModel.centerBannerItems.collectAsState()
    when (centerBannerResult) {
        is NetworkResult.Success -> {
            centerBannerList = centerBannerResult.data ?: emptyList()
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("TAG", "CenterBannerItem error : ${centerBannerResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }


    if (centerBannerList.isNotEmpty()) {
        CenterBannerItem(imageUrl = centerBannerList[bannerNumber - 1].image)
    }


}