package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.product_detail.Comment
import com.farzin.newdigikala.ui.components.Loading3Dots
import com.farzin.newdigikala.ui.components.OurLoading
import com.farzin.newdigikala.ui.theme.Green
import com.farzin.newdigikala.ui.theme.Orange
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.grayAlpha
import com.farzin.newdigikala.ui.theme.searchBarBg
import com.farzin.newdigikala.ui.theme.semiDarkText
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.DigitHelper
import com.farzin.newdigikala.viewmodel.ProductDetailViewModel

@Composable
fun AllCommentScreen(
    navController: NavController,
    id: String,
    commentCount: String,
    productDetailViewModel: ProductDetailViewModel = hiltViewModel(),
) {

    LaunchedEffect(true){
        productDetailViewModel.getAllProductComments(id)
    }

    val commentsList = productDetailViewModel.commentsList.collectAsLazyPagingItems()


    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navController.popBackStack() },
                tint = MaterialTheme.colors.darkText
            )


            Text(
                text = "${DigitHelper.digitByLang(commentCount)} ${stringResource(id = R.string.comment)}",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
            )


        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(MaterialTheme.colors.searchBarBg)
        )

        LazyColumn(Modifier.fillMaxSize()){
            items(commentsList){comment->
                if (comment != null) {
                    CommentsItem(item = comment)
                }
            }

            commentsList.loadState.apply {

                when{
                    refresh is LoadState.Loading->{
                        item {
                            val config = LocalConfiguration.current
                            OurLoading(config.screenHeightDp.dp, true)
                        }
                    }

                    append is LoadState.Loading->{
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Loading3Dots(isDark = true)
                            }
                        }
                    }

                    append is LoadState.Error->{
                        // todo error handle
                    }
                }


            }

        }
    }
}



@Composable
private fun CommentsItem(
    item: Comment,
) {
    val dateParts = item.updatedAt.substringBefore("T").split("-")
    val year = dateParts[0].toInt()
    val month = dateParts[1].toInt()
    val day = dateParts[2].toInt()

    val c = LocalContext.current

    var iconSuggestion = R.drawable.like
    var colorSuggestion = MaterialTheme.colors.Green
    var textSuggestion = c.getString(R.string.good_comment)
    var rotaion = 0f

    when (item.star) {
        in Int.MIN_VALUE..2 -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Orange
            textSuggestion = c.getString(R.string.bad_comment)
            rotaion = 180f
        }

        in 2..3 -> {
            iconSuggestion = R.drawable.info
            colorSuggestion = MaterialTheme.colors.grayAlpha
            textSuggestion = c.getString(R.string.so_so_comment)
            rotaion = 0f
        }

        in 3..Int.MAX_VALUE -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Green
            textSuggestion = c.getString(R.string.good_comment)
            rotaion = 0f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .width(MaterialTheme.spacing.large)
                    .background(colorSuggestion),
                text = DigitHelper.digitByLang(item.star.toString() + ".0"),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
                text = DigitHelper.digitByLang(DigitHelper.gregorianToJalali(year, month, day)),
                color = MaterialTheme.colors.semiDarkText,
                style = MaterialTheme.typography.h6,
            )
            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                Modifier
                    .size(20.dp)
                    .padding(horizontal = MaterialTheme.spacing.small),
                tint = MaterialTheme.colors.grayAlpha
            )
            Text(
                text = item.userName,
                color = MaterialTheme.colors.grayAlpha,
                style = MaterialTheme.typography.h6
            )
        }

        Divider(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large)
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f)
                .shadow(2.dp),
            color = Color.LightGray,
        )


        Row(
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.large
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconSuggestion),
                contentDescription = "",
                Modifier
                    .size(16.dp)
                    .rotate(rotaion),
                tint = colorSuggestion
            )
            Text(
                text = textSuggestion,
                Modifier
                    .padding(start = MaterialTheme.spacing.extraSmall),
                maxLines = 1,
                style = MaterialTheme.typography.h6,
                color = colorSuggestion
            )
        }


        Text(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large
                ),
            text = item.title,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h5,
        )
        Text(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large,
                    top = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.large,
                ),
            text = item.description,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h5,
        )


    }
}
