package com.farzin.newdigikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.home.StoreProduct
import com.farzin.newdigikala.ui.theme.*
import com.farzin.newdigikala.util.Constants
import com.farzin.newdigikala.util.DigitHelper

@Composable
fun MostFavoriteProductsOffer(item: StoreProduct,onClick:()->Unit) {

    Column(
        modifier = Modifier
            .width(170.dp)
            .padding(
                vertical = MaterialTheme.spacing.semiLarge,
                horizontal = MaterialTheme.spacing.semiSmall
            )
            .clickable { onClick() },
    ) {

        Row {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = MaterialTheme.spacing.small)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.extraSmall)
                ) {


                    Image(
                        painter = rememberAsyncImagePainter(item.image),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        contentScale = ContentScale.FillBounds
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.small)
                ) {

                    Text(
                        text = item.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(48.dp)
                            .padding(horizontal = MaterialTheme.spacing.small),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.darkText,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.in_stock),
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                                .padding(2.dp),
                            tint = MaterialTheme.colors.DarkCyan
                        )
                        Text(
                            text = item.seller,
                            style = MaterialTheme.typography.extraSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.semiDarkText,
                        )

                    }

                    Spacer(modifier = Modifier.height(10.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.small),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {

                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(24.dp)
                                .background(
                                    color = MaterialTheme.colors.DigikalaDarkRed,
                                    shape = CircleShape
                                )
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .wrapContentHeight(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = "${DigitHelper.digitByLangAndSeparator(item.discountPercent.toString())}%",
                                color = Color.White,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                            )
                        }


                        Column {

                            Row {
                                Text(
                                    text = DigitHelper.digitByLangAndSeparator(
                                        DigitHelper.calculateDiscount(
                                            item.price,
                                            item.discountPercent
                                        )
                                            .toString()
                                    ),
                                    style = MaterialTheme.typography.body2,
                                    fontWeight = FontWeight.SemiBold,
                                )

                                Icon(
                                    painter = currencyLogoChangeByLanguage(),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(MaterialTheme.spacing.semiLarge)
                                        .padding(horizontal = MaterialTheme.spacing.extraSmall)
                                )

                            }

                            Text(
                                text = DigitHelper.digitByLangAndSeparator(item.price.toString()),
                                color = Color.LightGray,
                                style = MaterialTheme.typography.body2,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }

                    }


                }


            }


            Divider(
                Modifier
                    .padding(start = MaterialTheme.spacing.semiMedium)
                    .width(1.dp)
                    .height(320.dp)
                    .alpha(0.4f),
                color = Color.LightGray
            )
        }


    }


}


@Composable
private fun currencyLogoChangeByLanguage(): Painter {
    return if (Constants.USER_LANGUAGE == Constants.ENGLISH_LANG) {
        painterResource(id = R.drawable.dollar)
    } else {
        painterResource(id = R.drawable.toman)
    }
}