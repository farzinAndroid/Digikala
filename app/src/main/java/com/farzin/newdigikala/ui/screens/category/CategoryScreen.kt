package com.farzin.newdigikala.ui.screens.category


import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import com.farzin.newdigikala.R
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


@Composable
private fun SwipeRefreshSection(
    viewModel: CategoryViewModel,
    navController: NavHostController
) {
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            refreshScope.launch {
                refreshDataFromServer(viewModel)
                Log.e("TAG", "swipeRefresh")
            }
        }) {


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