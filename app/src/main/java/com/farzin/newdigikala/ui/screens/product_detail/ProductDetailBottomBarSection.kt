package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.basket.CartItem
import com.farzin.newdigikala.data.model.basket.CartStatus
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.theme.DigikalaDarkRed
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.bottomBar
import com.farzin.newdigikala.ui.theme.digikalaRed
import com.farzin.newdigikala.ui.theme.elevation
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.DigitHelper.calculateDiscount
import com.farzin.newdigikala.util.DigitHelper.digitByLang
import com.farzin.newdigikala.util.DigitHelper.digitByLangAndSeparator
import com.farzin.newdigikala.viewmodel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailBottomBarSection(
    item: ProductDetail,
    basketViewModel: BasketViewModel = hiltViewModel(),
    navController: NavController
) {

    var price = 0L
    item.price?.let {
        price = it
    }

    var discountPercent = 0
    item.discountPercent?.let {
        discountPercent = it
    }

    var showAddToBasket by remember { mutableStateOf(true) }
    var isLaunchedEffectComplete by remember { mutableStateOf(false) }
    LaunchedEffect(true){
        basketViewModel.isItemInBasket(item._id ?: "").collectLatest {
            showAddToBasket = !it
            isLaunchedEffectComplete = true
        }
    }


    var itemCountInBasket by remember { mutableIntStateOf(0) }
    LaunchedEffect(true){
        basketViewModel.getItemCount(item._id ?: "").collectLatest {
            itemCountInBasket = it
        }
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


            Row {
                if (isLaunchedEffectComplete && showAddToBasket){
                    Button(
                        onClick = {
                            showAddToBasket = false
                            basketViewModel.insertCartItem(
                                CartItem(
                                    itemId = item._id ?: "",
                                    name = item.name ?: "",
                                    seller = item.seller ?: "",
                                    price = item.price ?: 0L,
                                    discountPercent = item.discountPercent ?: 0,
                                    image = item.imageSlider?.get(0)?.image ?: "",
                                    count = 1,
                                    cartStatus = CartStatus.CURRENT_CART
                                )
                            )
                        },
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
                }else if (isLaunchedEffectComplete && !showAddToBasket){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.DigikalaDarkRed),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = digitByLang(itemCountInBasket.toString()),
                                color = Color.White,
                                modifier = Modifier.padding(start = MaterialTheme.spacing.extraSmall)
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(start = MaterialTheme.spacing.small)
                        ) {
                            Text(
                                text = stringResource(R.string.in_your_basket),
                                color = Color.LightGray,
                                style = MaterialTheme.typography.h5
                            )

                            Text(
                                text = stringResource(R.string.view_in_carts),
                                color = MaterialTheme.colors.DigikalaDarkRed,
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(Screen.Basket.route)
                                    }
                            )
                        }
                    }
                }
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