package com.farzin.newdigikala.ui.screens.category


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.farzin.newdigikala.ui.screens.home.*
import com.farzin.newdigikala.viewmodel.CategoryViewModel
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(navController: NavHostController) {
    Category(navController = navController)
}

@Composable
fun Category(
    navController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel()
) {


    LaunchedEffect(true) {
        refreshDataFromServer(viewModel)
    }

    SwipeRefreshSection(viewModel, navController)

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SwipeRefreshSection(
    vm: CategoryViewModel,
    navController: NavHostController
) {
    val onRefresh = rememberCoroutineScope()
    val swipeRefreshState = rememberPullRefreshState(refreshing = false, onRefresh = {
        onRefresh.launch {
            refreshDataFromServer(vm)
            Log.e("TAG", "Refresh")
        }
    })

    Box(
        Modifier.pullRefresh(swipeRefreshState)
    ){


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {

            item { SearchBarSection() }
            item { SubCategorySection() }

        }

    }
}


private suspend fun refreshDataFromServer(viewModel: CategoryViewModel) {
    viewModel.getAllDataFromServer()
}