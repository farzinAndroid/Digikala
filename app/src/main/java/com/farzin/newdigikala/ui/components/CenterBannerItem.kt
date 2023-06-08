package com.farzin.newdigikala.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun CenterBannerItem(imageUrl: String) {

    Card(
        shape = MaterialTheme.roundedShape.semiMedium,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(MaterialTheme.spacing.medium)
    ){

        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription ="" ,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }

}

@Composable
fun CenterBannerItem(painter: Painter) {

    Card(
        shape = MaterialTheme.roundedShape.semiMedium,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(MaterialTheme.spacing.medium)
    ){

        Image(
            painter = painter,
            contentDescription ="" ,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }

}