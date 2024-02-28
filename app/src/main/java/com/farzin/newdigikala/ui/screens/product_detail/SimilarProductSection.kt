package com.farzin.newdigikala.ui.screens.product_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.newdigikala.data.model.home.StoreProduct
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.R
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.screens.home.MostFavoriteProductsOffer
import com.farzin.newdigikala.ui.screens.home.MostFavoriteProductsShowMore
import com.farzin.newdigikala.ui.theme.DarkCyan
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SimilarProductSection(
    categoryId: String,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    var similarProductList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    viewModel.getSimilarProducts(categoryId)


    LaunchedEffect(true) {

        viewModel.similarProducts.collectLatest { similarProductResult ->
            when (similarProductResult) {
                is NetworkResult.Success -> {
                    similarProductList = similarProductResult.data ?: emptyList()
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("TAG", "SimilarProductSection error : ${similarProductResult.message}")
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }

    }


    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.small)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.extraSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.similar_product),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
            )

        }


        LazyRow {
            items(similarProductList) { item ->
                MostFavoriteProductsOffer(item){
                    navController.navigate(Screen.ProductDetail.withArgs(item._id))
                }
            }
        }

    }


}