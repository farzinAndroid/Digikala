package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import com.farzin.newdigikala.ui.theme.DarkCyan
import com.farzin.newdigikala.ui.theme.DigikalaLightGreen
import com.farzin.newdigikala.ui.theme.Gold
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.grayAlpha
import com.farzin.newdigikala.ui.theme.semiDarkText
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.DigitHelper.digitByLang

@Composable
fun ProductDetailHeaderSection(
    item: ProductDetail,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "${stringResource(R.string.category)} / ${item.category}",
            style = Typography.h6,
            color = MaterialTheme.colors.DarkCyan,
        )

        Text(
            text = item.name.toString(),
            style = Typography.h3,
            color = MaterialTheme.colors.darkText,
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.small
                ),
            fontWeight = FontWeight.Bold,
            maxLines = 2
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(R.drawable.star),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp),
                tint = MaterialTheme.colors.Gold
            )

            Text(
                text = digitByLang(item.star.toString()),
                style = Typography.h6,
                color = MaterialTheme.colors.darkText,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
            )

            Text(
                text = "(${digitByLang(item.starCount.toString())})",
                style = Typography.h6,
                color = Color.Gray,
            )

            Icon(
                painter = painterResource(R.drawable.dot),
                contentDescription = "",
                tint = MaterialTheme.colors.grayAlpha,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(5.dp)

            )


            Text(
                text = "${digitByLang(item.commentCount.toString())} ${stringResource(R.string.comment)}",
                style = Typography.h6,
                color = MaterialTheme.colors.DarkCyan,
            )

            Icon(
                painter = painterResource(R.drawable.dot),
                contentDescription = "",
                tint = MaterialTheme.colors.grayAlpha,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(5.dp)

            )



            Text(
                text = "${digitByLang(item.viewCount.toString())} ${stringResource(R.string.view)}",
                style = Typography.h6,
                color = MaterialTheme.colors.DarkCyan,
            )

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.small,
                )
        ) {

            Icon(
                painter = painterResource(R.drawable.like),
                contentDescription = "",
                tint = MaterialTheme.colors.DigikalaLightGreen,
                modifier = Modifier
                    .size(20.dp)
            )



            val percent = ((item.star?.div(5.0) ?: 0.0) * 100).toInt()
            val users = (percent * (item.starCount?.toDouble() ?: 0.0)/100).toInt()

            val text = String.format(
                "%d%% (%d) نفر از خریداران این کالا را پیشنهاد کردند.",
                percent,
                users
            )


            Text(
                text = digitByLang(text),
                style = Typography.h6,
                color = MaterialTheme.colors.semiDarkText,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
            )

        }


        Divider(
            color = MaterialTheme.colors.grayAlpha,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        )


    }

}