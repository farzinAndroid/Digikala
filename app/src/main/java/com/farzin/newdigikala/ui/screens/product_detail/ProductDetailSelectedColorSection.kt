package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.farzin.newdigikala.data.model.product_detail.ProductColor
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun ProductDetailSelectedColorSection(
    colors: List<ProductColor>,
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small,
            )
    ) {

        Text(
            text = "تست",
            color = MaterialTheme.colors.darkText,
            style = Typography.h5,
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.small),
            fontWeight = FontWeight.SemiBold
        )


        LazyRow(modifier = Modifier.fillMaxWidth()) {

            items(colors) { color ->
                ColorChipItem(color)
            }

        }

    }

}


