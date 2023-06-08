package com.farzin.newdigikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.farzin.newdigikala.data.model.home.Slider
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.viewmodel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProposalCardSection(
    viewModel: HomeViewModel = hiltViewModel()
) {

    var bannersList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val bannersResult by viewModel.banners.collectAsState()
    when (bannersResult) {
        is NetworkResult.Success -> {
            bannersList = bannersResult.data ?: emptyList()
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "Banners Section error : ${bannersResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }

    //LazyVerticalGrid
    FlowRow(
        maxItemsInEachRow = 2,
        modifier = Modifier
            .fillMaxWidth()
            .height(290.dp)
            .padding(MaterialTheme.spacing.small)
    ) {
        for (item in bannersList) {
            ProposalCardItem(item)
        }
    }

}

@Composable
fun ProposalCardItem(imgLink: Slider) {
    Card(
        shape = MaterialTheme.roundedShape.semiMedium,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(140.dp)
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.small
            ),
    ) {

        Image(
            painter = rememberAsyncImagePainter(imgLink.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }
}