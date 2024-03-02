package com.farzin.newdigikala.ui.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.ui.theme.settingArrow
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun MenuRowItem(
    icon: @Composable ()->Unit,
    text: String,
    isHaveDivider: Boolean,
    onClick:()->Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = MaterialTheme.spacing.medium)
            .clickable { onClick() }
    ) {

        Column(
            Modifier
                .fillMaxHeight()
                .weight(0.1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            icon()
        }

        Column(
            Modifier
                .fillMaxHeight()
                .weight(0.9f)
                .padding(horizontal = MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.Center,
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    text = text
                )

                Icon(
                    Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.settingArrow
                )


            }

            if (isHaveDivider) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .alpha(0.4f),
                    color = Color.LightGray,
                )
            }

        }


    }

}