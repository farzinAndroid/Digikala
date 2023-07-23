package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.product_detail.Comment
import com.farzin.newdigikala.ui.theme.Green
import com.farzin.newdigikala.ui.theme.Orange
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.extraSmall
import com.farzin.newdigikala.ui.theme.grayAlpha
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.semiDarkText
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.DigitHelper

@Composable
fun CommentsCard(
    comment: Comment,
) {

    val dateParse = comment.updatedAt.substringBefore("T").split("-")
    val year = dateParse[0].toInt()
    val month = dateParse[1].toInt()
    val day = dateParse[2].toInt()



    val context = LocalContext.current

    var iconSuggestion = R.drawable.like
    var colorSuggestion = MaterialTheme.colors.Green
    var textSuggestion = context.getString(R.string.good_comment)
    var iconRotation = 0f

    when (comment.star) {
        in Int.MIN_VALUE..2 -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Orange
            textSuggestion = context.getString(R.string.bad_comment)
            iconRotation = 180f
        }
        in 2..3 -> {
            iconSuggestion = R.drawable.info
            colorSuggestion = MaterialTheme.colors.semiDarkText
            textSuggestion = context.getString(R.string.so_so_comment)
            iconRotation = 0f
        }
        in 3..Int.MAX_VALUE -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Green
            textSuggestion = context.getString(R.string.good_comment)
            iconRotation = 0f
        }
    }



    Card(
        modifier = Modifier
            .width(260.dp)
            .height(210.dp)
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.medium,
            ),
        shape = MaterialTheme.roundedShape.small,
        elevation = 2.dp

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {

            Text(
                text = comment.title,
                style = Typography.h4,
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.extraSmall),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(iconSuggestion),
                    modifier = Modifier
                        .size(20.dp)
                        .rotate(iconRotation),
                    contentDescription = "",
                    tint = colorSuggestion
                )


                Text(
                    text = textSuggestion,
                    color = colorSuggestion,
                    style = Typography.extraSmall,
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.small),
                    maxLines = 1
                )

            }


            Text(
                text = comment.description,
                color = MaterialTheme.colors.semiDarkText,
                style = Typography.h6,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = MaterialTheme.spacing.small),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = DigitHelper.gregorianToJalali(year, month, day),
                    style = Typography.h6,
                    color = MaterialTheme.colors.semiDarkText,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    painter = painterResource(R.drawable.dot),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(horizontal = MaterialTheme.spacing.small),
                    tint = MaterialTheme.colors.grayAlpha
                )


                Text(
                    text = comment.userName,
                    style = Typography.h6,
                    color = MaterialTheme.colors.grayAlpha,
                )

            }


        }

    }

}