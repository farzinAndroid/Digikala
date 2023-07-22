package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.bottomBar
import com.farzin.newdigikala.ui.theme.digikalaRed
import com.farzin.newdigikala.ui.theme.elevation
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.DigitHelper
import com.farzin.newdigikala.util.DigitHelper.calculateDiscount
import com.farzin.newdigikala.util.DigitHelper.digitByLang
import com.farzin.newdigikala.util.DigitHelper.digitByLangAndSeparator

@Composable
fun ProductDetailBottomBarSection(
    item: ProductDetail,
) {

    var price = 0L
    item.price?.let {
        price = it
    }

    var discountPercent = 0
    item.discountPercent?.let {
        discountPercent = it
    }

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        elevation = MaterialTheme.elevation.small,
        backgroundColor = MaterialTheme.colors.bottomBar
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.biggerSmall)
                .padding(horizontal = MaterialTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = stringResource(R.string.add_to_basket),
                    style = Typography.h5,
                    color = Color.White,
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.extraSmall)
                )
            }


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.digikalaRed, CircleShape)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .wrapContentHeight(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "${digitByLang(discountPercent.toString())}%",
                            color = Color.White,
                            style = Typography.h6,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                        )
                    }

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

                    Text(
                        text = digitByLangAndSeparator(price.toString()),
                        color = Color.Gray,
                        style = Typography.body2,
                        textDecoration = TextDecoration.LineThrough
                    )

                }

                Row(verticalAlignment = Alignment.CenterVertically) {


                    Text(
                        text = digitByLangAndSeparator(
                            calculateDiscount(
                                price,
                                discountPercent
                            ).toString()
                        ).toString(),
                        style = Typography.body1,
                        fontWeight = FontWeight.SemiBold
                    )

                    Image(
                        painter = painterResource(R.drawable.toman),
                        contentDescription = "",
                        Modifier
                            .size(MaterialTheme.spacing.semiLarge)
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    )

                }

            }


        }

    }


}