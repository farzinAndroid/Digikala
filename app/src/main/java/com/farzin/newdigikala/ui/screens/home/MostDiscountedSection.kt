package com.farzin.newdigikala.ui.screens.home

import android.util.Log
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
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.viewmodel.HomeViewModel
import com.farzin.newdigikala.R
import com.farzin.newdigikala.ui.theme.darkText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MostDiscountedSection(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var mostDiscountedList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val mostDiscountedListResult by viewModel.mostDiscountedItems.collectAsState()
    when (mostDiscountedListResult) {
        is NetworkResult.Success -> {
            mostDiscountedList = mostDiscountedListResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("TAG", "MostDiscountedSection error : ${mostDiscountedListResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {


        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small),
            text = stringResource(id = R.string.most_discounted_products),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.h3,
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

            for (item in mostDiscountedList) {
                MostDiscountedCard(item)
            }

        }

    }

}