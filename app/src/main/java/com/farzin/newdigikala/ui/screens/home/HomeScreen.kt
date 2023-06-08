package com.farzin.newdigikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.farzin.newdigikala.util.Constants
import com.farzin.newdigikala.util.LocaleUtils
import com.farzin.newdigikala.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    Home(navController)

}

@Composable
fun Home(navController: NavHostController, vm: HomeViewModel = hiltViewModel()) {


    LocaleUtils.setLocale(LocalContext.current, Constants.USER_LANGUAGE)

    LaunchedEffect(true) {
        getDataFromServer(vm)
    }

    SwipeRefreshSection(vm, navController)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SwipeRefreshSection(vm: HomeViewModel, navController: NavHostController) {
    val onRefresh = rememberCoroutineScope()
    val swipeRefreshState = rememberPullRefreshState(refreshing = false, onRefresh = {
        onRefresh.launch {
            getDataFromServer(vm)
            Log.e("TAG", "Refresh")
        }
    })


    Box(
        Modifier.pullRefresh(swipeRefreshState)
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
                .verticalScroll(rememberScrollState())
        ) {

            SearchBarSection()
            TopSliderSection()
            ShowcaseSection(navController)
            AmazingOfferSection()
            ProposalCardSection()
            SuperMarketOfferSection()
            CategoryListSection()
            CenterBannerSection(1)
            BestSellerOfferSection()
            CenterBannerSection(2)
            MostFavoriteProductSection()
            CenterBannerSection(3)
            MostVisitedOfferSection()
            CenterBannerSection(4)
            CenterBannerSection(5)
            MostDiscountedSection()


        }

        PullRefreshIndicator(
            refreshing = false,
            state = swipeRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}

private suspend fun getDataFromServer(vm: HomeViewModel) {
    vm.getAllDataFromServer()
}