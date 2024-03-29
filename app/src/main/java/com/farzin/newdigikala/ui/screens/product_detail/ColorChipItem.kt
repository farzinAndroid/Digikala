package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.data.model.product_detail.ProductColor
import com.farzin.newdigikala.ui.theme.CursorColor
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun ColorChipItem(
    item: ProductColor,
    isSelected: Boolean,
    onSelected: (String) -> Unit,
) {

    Surface(
        elevation = 1.dp,
        shape = MaterialTheme.roundedShape.biggerMedium,
        modifier = if (isSelected)
            Modifier
                .padding(MaterialTheme.spacing.extraSmall)
                .border(1.dp, MaterialTheme.colors.CursorColor, CircleShape)
        else
            Modifier
                .padding(MaterialTheme.spacing.extraSmall)

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = { onSelected(item.color) }
                )
                .padding(MaterialTheme.spacing.small)
        ) {

            Canvas(
                modifier = Modifier
                    .size(20.dp)
                    .border(1.dp, Color.Gray, CircleShape),
                onDraw = {
                    drawCircle(
                        color = Color(
                            ("ff" + item.code.removePrefix("#").lowercase()).toLong(16)
                        )
                    )
                }
            )

            Text(
                text = item.color,
                style = Typography.h6,
                color = MaterialTheme.colors.darkText,
            )

        }

    }

}