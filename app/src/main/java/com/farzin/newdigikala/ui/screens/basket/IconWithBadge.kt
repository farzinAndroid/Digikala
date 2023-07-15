package com.farzin.newdigikala.ui.screens.basket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.ui.theme.digikalaRed
import com.farzin.newdigikala.ui.theme.extraSmall
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.DigitHelper.digitByLangAndSeparator

@Composable
fun IconWithBadge(
    cartCounter: Int,
    icon: Painter,
) {

    Box(
        modifier = Modifier
            .height(28.dp)
    ) {

        Box(
            modifier = Modifier
                .height(28.dp)
                .width(36.dp),
            contentAlignment = Alignment.TopCenter,
        ) {
            Icon(
                painter = icon,
                contentDescription = "",
                modifier = Modifier
                    .height(24.dp),
            )
        }

        Box(
            modifier = Modifier
                .height(28.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Card(
                shape = MaterialTheme.roundedShape.small,
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text(
                    text = digitByLangAndSeparator(cartCounter.toString()),
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.digikalaRed)
                        .height(16.dp)
                        .padding(horizontal = MaterialTheme.spacing.semiSmall)
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.extraSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }


    }

}