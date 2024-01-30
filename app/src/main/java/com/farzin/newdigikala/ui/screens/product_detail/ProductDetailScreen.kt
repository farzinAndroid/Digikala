package com.farzin.newdigikala.ui.screens.product_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.newdigikala.data.model.product_detail.Comment
import com.farzin.newdigikala.data.model.product_detail.ProductColor
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import com.farzin.newdigikala.data.model.product_detail.SliderImage
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.ui.components.OurLoading
import com.farzin.newdigikala.ui.screens.home.isScreenDark
import com.farzin.newdigikala.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailScreen(
    navController: NavController,
    id: String,
    productDetailViewModel: ProductDetailViewModel = hiltViewModel(),
) {

    var loading by remember {
        mutableStateOf(false)
    }

    var categoryId by remember {
        mutableStateOf("")
    }

    var description by rememberSaveable {
        mutableStateOf("")
    }

    var technical by remember {
        mutableStateOf("")
    }

    var productDetailList by remember {
        mutableStateOf(ProductDetail())
    }

    var sliderImage by remember {
        mutableStateOf<List<SliderImage>>(emptyList())
    }

    var colorList by remember {
        mutableStateOf<List<ProductColor>>(emptyList())
    }

    var commentsList by remember {
        mutableStateOf<List<Comment>>(emptyList())
    }

    var commentsCount by remember {
        mutableStateOf(0)
    }


    LaunchedEffect(true) {

        productDetailViewModel.getProductById(id)

        productDetailViewModel.productDetails.collectLatest { productDetailResult ->
            when (productDetailResult) {
                is NetworkResult.Success -> {
                    productDetailList = productDetailResult.data!!
                    sliderImage = productDetailResult.data.imageSlider ?: emptyList()
                    colorList = productDetailResult.data.colors ?: emptyList()
                    commentsList = productDetailResult.data.comments ?: emptyList()
                    commentsCount = productDetailResult.data.commentCount ?: 0
                    categoryId = productDetailResult.data.categoryId ?: ""
                    description = productDetailResult.data.description ?: ""
                    technical = productDetailResult.data.technicalFeatures.toString()
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
            topBar = {
                     ProductTopBarSection(navController)
            },
            content = {
                LazyColumn(
                    modifier = Modifier.padding(bottom = 70.dp)
                ) {
                    item { ProductDetailImageSlider(sliderImage) }
                    item { ProductDetailHeaderSection(item = productDetailList) }
                    item { ProductDetailSelectedColorSection(colors = colorList) }
                    item { SellerInfoSection() }
                    item { SimilarProductSection(categoryId = categoryId) }
                    item { ProductDescriptionSection(navController, description, technical) }
                    item {
                        ProductCommentsSection(
                            comments = commentsList,
                            commentsCount = commentsCount,
                            productId = id,
                            navController = navController
                        )
                    }
                    item { ProductSetCommentsSection(navController, productDetailList) }
                }
            }
        )

    }


}