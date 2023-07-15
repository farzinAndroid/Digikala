package com.farzin.newdigikala.ui.screens.product_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.newdigikala.data.model.home.Slider
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import com.farzin.newdigikala.data.model.product_detail.SliderImage
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.ui.components.OurLoading
import com.farzin.newdigikala.ui.screens.home.isScreenDark
import com.farzin.newdigikala.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailSectionScreen(
    navController: NavController,
    id: String,
    productDetailViewModel: ProductDetailViewModel = hiltViewModel(),
) {

    var loading by remember {
        mutableStateOf(false)
    }

    var productDetailList by remember {
        mutableStateOf<ProductDetail>(ProductDetail())
    }

    var sliderImage by remember {
        mutableStateOf<List<SliderImage>>(emptyList())
    }


    LaunchedEffect(true) {

        productDetailViewModel.getProductById(id)

        productDetailViewModel.productDetails.collectLatest { productDetailResult ->
            when (productDetailResult) {
                is NetworkResult.Success -> {
                    productDetailList = productDetailResult.data!!
                    sliderImage = productDetailResult.data.imageSlider!!
                    loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("TAG", "productDetailList error : ${productDetailResult.message}")
                    loading = false
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }


    if (loading) {
        val config = LocalConfiguration.current
        OurLoading(height = config.screenHeightDp.dp, isDark = isScreenDark())
    } else {

        Scaffold(
            bottomBar = {
                ProductDetailBottomBarSection(item = productDetailList)
            },
            content = {
                LazyColumn {
                    item { ProductDetailImageSlider(sliderImage) }
                }
            }
        )

    }


}