package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.farzin.newdigikala.data.model.product_detail.SliderImage
import com.farzin.newdigikala.ui.theme.LocalShape
import com.farzin.newdigikala.ui.theme.LocalSpacing
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetailImageSlider(
    imageSlider: List<SliderImage>,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val pagerState = rememberPagerState()
            var imageUrl by remember {
                mutableStateOf("")
            }

            Box {

                HorizontalPager(
                    count = imageSlider.size,
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = LocalSpacing.current.medium),
                    modifier = Modifier
                        .fillMaxWidth()
                ) { index ->
                    imageUrl = imageSlider[index].image
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


        }
    }
}


