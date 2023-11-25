package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.product_detail.Comment
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.theme.LightCyan
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun ProductCommentsSection(
    comments:List<Comment>,
    commentsCount:Int,
    navController: NavController,
    productId:String
) {

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
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.semiLarge),
            verticalAlignment =Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(R.string.user_comments),
                style = Typography.h4,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText
            )


            Text(
                text = "$commentsCount ${stringResource(R.string.comment)}",
                style = Typography.h5,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.LightCyan,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.AllComment.withArgs(productId,commentsCount))
                    }
            )

        }


        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ){

            items(comments){comment->
                CommentsCard(comment)
            }

            item {
                CommentsCardShowMore(){
                    navController.navigate(Screen.AllComment.withArgs(productId,commentsCount))
                }
            }


        }

    }


}