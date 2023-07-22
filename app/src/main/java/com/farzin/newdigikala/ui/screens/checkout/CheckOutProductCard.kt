package com.farzin.newdigikala.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.newdigikala.data.model.basket.CartItem
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.extraSmall
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.DigitHelper

@Composable
fun CheckOutProductCard(
    cart: CartItem,
) {

    Box(
        modifier = Modifier
            .size(75.dp)
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(cart.image),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }

        Box(
            modifier = Modifier
                .size(75.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = DigitHelper.digitByLang(cart.count.toString()),
                fontWeight = FontWeight.Bold,
                style = Typography.extraSmall,
                color = Color.Black
            )
        }

    }

    Divider(
        color = Color.Gray,
        modifier = Modifier
            .height(70.dp)
            .alpha(0.4f)
            .width(1.dp)

    )

}