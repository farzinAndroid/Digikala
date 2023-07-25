package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.grayAlpha
import com.farzin.newdigikala.ui.theme.grayCategory
import com.farzin.newdigikala.ui.theme.semiDarkText
import com.farzin.newdigikala.ui.theme.settingArrow
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun ProductSetCommentsSection(
    navController: NavController,
    item:ProductDetail
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.semiLarge,
                vertical = MaterialTheme.spacing.medium
            )
            .clickable {
                navController.navigate(Screen.SetNewComment.route + "?productId=${item._id}?productName=${item.name}?imageUrl=${item.imageSlider?.get(0)?.image}")
            }
    ) {
        
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            
            Icon(
                painter = painterResource(R.drawable.comment),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
            )


            Text(
                text = stringResource(R.string.write_your_comment),
                style = Typography.h5,
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp)
            )


            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.settingArrow
            )
            
        }

        Text(
            text = stringResource(R.string.comment_desc),
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.small,
                    top = MaterialTheme.spacing.small
                ),
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colors.semiDarkText,
            style = Typography.h6
        )

        Spacer(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large,
                    top = MaterialTheme.spacing.medium
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )

        
    }

}