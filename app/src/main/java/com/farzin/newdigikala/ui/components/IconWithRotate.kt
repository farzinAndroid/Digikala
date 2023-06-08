package com.farzin.newdigikala.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.util.Constants.ENGLISH_LANG
import com.farzin.newdigikala.util.Constants.USER_LANGUAGE

@Composable
fun IconWithRotate(imageVector: ImageVector) {

    if (USER_LANGUAGE == ENGLISH_LANG) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .graphicsLayer(
                    rotationZ = 180f
                )
        )
    } else {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = Color.White
        )
    }


}


@Composable
fun IconWithRotate(painter: Painter, tint: Color) {

    if (USER_LANGUAGE == ENGLISH_LANG) {
        Icon(
            painter = painter,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .graphicsLayer(
                    rotationZ = 180f
                )
                .size(40.dp, 40.dp)
        )
    } else {
        Icon(
            painter = painter,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .size(40.dp, 40.dp)
        )
    }


}