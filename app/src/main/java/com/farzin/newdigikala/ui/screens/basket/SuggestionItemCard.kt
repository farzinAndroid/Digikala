package com.farzin.newdigikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun SuggestionItemCard(
    item: StoreProduct,
    onAddClick: (StoreProduct) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f),
        elevation = 1.dp
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.extraSmall)
            ) {

                Box {
                    Image(
                        painter = rememberAsyncImagePainter(item.image),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        contentScale = ContentScale.Fit
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.small),

                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {


                        Surface(
                            modifier = Modifier
                                .padding(MaterialTheme.spacing.extraSmall)
                                .size(26.dp)
                                .clip(CircleShape)
                                .border(1.dp, MaterialTheme.colors.digikalaRed, CircleShape)
                                .clickable{
                                    onAddClick(item)
                                }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "",
                                tint = MaterialTheme.colors.digikalaRed
                            )
                        }


                    }
                }


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
                        .fillMaxWidth()
                        .padding(start = MaterialTheme.spacing.extraSmall),
                    horizontalArrangement = Arrangement.Start,
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
                            text = "${DigitHelper.digitByLocateAndSeparator(item.discountPercent.toString())}%",
                            color = Color.White,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                        )
                    }


                    Column {

                        Row {
                            Text(
                                text = DigitHelper.digitByLocateAndSeparator(
                                    DigitHelper.applyDiscount(item.price, item.discountPercent)
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
                            text = DigitHelper.digitByLocateAndSeparator(item.price.toString()),
                            color = Color.LightGray,
                            style = MaterialTheme.typography.body2,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }

                }


            }


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