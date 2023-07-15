package com.farzin.newdigikala.ui.screens.home


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.farzin.newdigikala.data.model.home.Slider
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.ui.components.OurLoading
import com.farzin.newdigikala.ui.theme.LocalShape
import com.farzin.newdigikala.ui.theme.LocalSpacing
import com.farzin.newdigikala.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopSliderSection(vm: HomeViewModel = hiltViewModel()) {

    var loading by remember {
        mutableStateOf(false)
    }

    var sliderList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }


    val sliderResult by vm.slider.collectAsState()

    when (sliderResult) {
        is NetworkResult.Success -> {
            sliderList = sliderResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("TAG", "Top slider error : ${sliderResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (loading) {
        val config = LocalConfiguration.current
        OurLoading(height = config.screenHeightDp.dp, isDark = isScreenDark())
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(
                        horizontal = LocalSpacing.current.extraSmall,
                        vertical = LocalSpacing.current.small
                    )
            ) {
                val pagerState = rememberPagerState()
                var imageUrl by remember {
                    mutableStateOf("")
                }


                Box {

                    HorizontalPager(
                        count = sliderList.size,
                        state = pagerState,
                        contentPadding = PaddingValues(horizontal = LocalSpacing.current.medium),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) { index ->
                        imageUrl = sliderList[index].image
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            val painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = imageUrl)
                                    .scale(Scale.FIT)
                                    .build()
                            )
                            Image(
                                painter = painter, contentDescription = "", modifier = Modifier
                                    .padding(LocalSpacing.current.small)
                                    .clip(LocalShape.current.medium)
                                    .fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }

                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        activeColor = Color.Black,
                        inactiveColor = Color.LightGray,
                        indicatorWidth = LocalSpacing.current.small,
                        indicatorHeight = LocalSpacing.current.small,
                        indicatorShape = CircleShape,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(LocalSpacing.current.semiLarge)
                    )

                }


                val isDraggedState by pagerState.interactionSource.collectIsDraggedAsState()
                LaunchedEffect(isDraggedState) {
                    snapshotFlow { isDraggedState }
                        .filter { !isDraggedState }
                        .collectLatest { isDragged ->
                            while (true) {
                                delay(6000)
                                var newPosition = pagerState.currentPage + 1
                                if (newPosition > sliderList.size - 1) newPosition = 0
                                pagerState.animateScrollToPage(newPosition)
                            }
                        }
                }
            }
        }
    }


}

@Composable
fun isScreenDark(): Boolean {
    return if (MaterialTheme.colors.isLight) {
        true
    } else
        false

}

