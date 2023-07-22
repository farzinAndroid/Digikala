package com.farzin.newdigikala.ui.screens.basket

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*


import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.newdigikala.data.model.home.StoreProduct
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.searchBarBg
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.viewmodel.BasketViewModel
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.basket.CartItem
import com.farzin.newdigikala.data.model.basket.CartStatus

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SuggestListSection(
    viewModel: BasketViewModel = hiltViewModel(),
) {


    viewModel.getSuggestedItems()

    var suggestedList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val suggestedItemResult by viewModel.suggestedList.collectAsState()
    when (suggestedItemResult) {
        is NetworkResult.Success -> {
            suggestedList = suggestedItemResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("TAG", "SuggestListSection error : ${suggestedItemResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .background(MaterialTheme.colors.searchBarBg)
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        text = stringResource(id = R.string.suggestion_for_you),
        textAlign = TextAlign.Right,
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colors.darkText,
    )

    FlowRow(
        maxItemsInEachRow = 2,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start
    ) {

        for (item in suggestedList) {
            SuggestionItemCard(item) {
                viewModel.insertCartItem(
                    CartItem(
                        it._id,
                        it.name,
                        it.seller,
                        it.price,
                        it.discountPercent,
                        it.image,
                        1,
                        CartStatus.CURRENT_CART
                    )
                )
            }
        }

    }


}