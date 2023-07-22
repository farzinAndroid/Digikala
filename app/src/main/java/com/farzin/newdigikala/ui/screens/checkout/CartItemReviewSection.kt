package com.farzin.newdigikala.ui.screens.checkout

import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Start
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.basket.CartDetails
import com.farzin.newdigikala.data.model.basket.CartItem
import com.farzin.newdigikala.ui.theme.DarkCyan
import com.farzin.newdigikala.ui.theme.DigikalaLightRed
import com.farzin.newdigikala.ui.theme.DigikalaLightRedText
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.extraSmall
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.DigitHelper

@Composable
fun CartItemReviewSection(
    currentCartItem: List<CartItem>,
    cartDetails: CartDetails,
    onClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = stringResource(R.string.deliveryAndTimeMethod),
            style = Typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .align(Alignment.Start),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.darkText
        )


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            shape = MaterialTheme.shapes.small
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.small),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = DigitHelper.digitByLang(stringResource(R.string.delivery_1)),
                    fontWeight = FontWeight.Bold,
                    style = Typography.h5,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.medium)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(R.drawable.k1),
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp),
                        tint = MaterialTheme.colors.DigikalaLightRedText
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                    Text(
                        text = stringResource(R.string.fast_send),
                        fontWeight = FontWeight.Bold,
                        style = Typography.extraSmall,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.DigikalaLightRedText
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                    Text(
                        text = "${DigitHelper.digitByLangAndSeparator(cartDetails.totalCount.toString())} ${
                            stringResource(

                                R.string.goods
                            )
                        }",
                        style = Typography.extraSmall,
                        textAlign = TextAlign.Start,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.small)
                    )

                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .padding(vertical = MaterialTheme.spacing.small),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    items(currentCartItem) { item ->
                        CheckOutProductCard(item)
                    }
                }

                Row {
                    Text(
                        text = stringResource(R.string.ready_to_send),
                        style = Typography.extraSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(top = MaterialTheme.spacing.small)
                    )

                    Text(
                        text = " : ${stringResource(R.string.pishtaz_post)} (${stringResource(R.string.delivery_delay)})",
                        style = Typography.extraSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(top = MaterialTheme.spacing.small)
                    )
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(
                            top = MaterialTheme.spacing.semiMedium,
                            bottom = MaterialTheme.spacing.small,
                        )
                        .clickable {
                            onClick()
                        }
                ) {
                    Text(
                        text = stringResource(R.string.choose_time),
                        style = Typography.h5,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.DarkCyan
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = "",
                        tint = MaterialTheme.colors.DarkCyan,
                        modifier = Modifier
                            .size(12.dp),
                    )
                }

            }

        }


    }


}