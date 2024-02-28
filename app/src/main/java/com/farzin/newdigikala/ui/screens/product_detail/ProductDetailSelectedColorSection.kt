package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.product_detail.ProductColor
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun ProductDetailSelectedColorSection(
    colors: List<ProductColor>,
) {


    var colorState by remember { mutableStateOf<List<ProductColor>>(emptyList()) }
    colorState = colors

    var selectedColor by remember { mutableStateOf<ProductColor?>(null) }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small,
            )
    ) {

        Text(
            text = "${stringResource(R.string.color)} : ${
                if (selectedColor != null) selectedColor?.color else stringResource(
                    R.string.not_selected
                )
            }",
            color = MaterialTheme.colors.darkText,
            style = Typography.h5,
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.small),
            fontWeight = FontWeight.SemiBold
        )


        LazyRow(modifier = Modifier.fillMaxWidth()) {

            items(colorState) { color ->
                ColorChipItem(
                    item = color,
                    isSelected = selectedColor?.color == color.color,
                    onSelected = { colorName ->
                        colorState.forEach {
                            if (it.color == colorName) {
                                selectedColor = it
                            }
                        }
                    }
                )
            }

        }

    }

}


