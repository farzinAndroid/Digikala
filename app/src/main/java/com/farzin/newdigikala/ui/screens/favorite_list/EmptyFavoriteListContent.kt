package com.farzin.newdigikala.ui.screens.favorite_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.R

@Composable
fun EmptyFavoriteListContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.no_fav_item
            ),
            contentScale = ContentScale.Fit,
            contentDescription = "",
            modifier = Modifier
                .height(600.dp)
                .width(500.dp)
        )
    }
}
