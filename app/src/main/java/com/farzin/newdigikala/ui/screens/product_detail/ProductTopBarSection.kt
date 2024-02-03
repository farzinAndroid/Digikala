package com.farzin.newdigikala.ui.screens.product_detail

import android.content.Context
import android.content.Intent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.product_detail.Price
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.DigitHelper
import com.google.gson.Gson

@Composable
fun ProductTopBarSection(navController: NavController, product: ProductDetail) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .weight(0.6f),
            horizontalArrangement = Arrangement.Start
        ) {

            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(R.drawable.exit),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }

        }

        Row(
            modifier = Modifier
                .weight(0.4f),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                navController.navigate(Screen.Basket.route)
            }) {
                Icon(
                    painter = painterResource(R.drawable.basket),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }

            var checked by remember { mutableStateOf(false) }

            IconToggleButton(
                checked = checked,
                onCheckedChange = {
                    checked = !checked
                }
            ) {

                val transition = updateTransition(checked, "icon transition")
                val tint by transition.animateColor(label = "icon transition") { isChecked ->
                    if (isChecked) Color.Red else MaterialTheme.colors.darkText
                }

                Icon(
                    imageVector = if (checked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp),
                    tint = tint
                )
            }


            var expanded by remember { mutableStateOf(false) }

            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    painter = painterResource(R.drawable.menu_dots),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                val priceListJsonString = Gson().toJson(product.priceList)
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        navController.navigate(
                            Screen.Chart.route
                                    + "?jsonString=${priceListJsonString}"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.extraSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.chart),
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                        Text(
                            text = stringResource(R.string.price_chart),
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText
                        )


                    }
                }

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        shareToSocialMedia(
                            context = context,
                            productName = product.name!!,
                            productPrice = DigitHelper.digitByLangAndSeparator(product.price.toString()),
                            url = "https://truelearn.ir/"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.extraSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.share),
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                        Text(
                            text = stringResource(R.string.share_product),
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText
                        )


                    }
                }
            }


        }


    }

}


fun shareToSocialMedia(
    context: Context,
    productName: String,
    productPrice: String,
    url: String,
) {

    val shareIntent = Intent(Intent.ACTION_SEND)

    shareIntent.type = "text/plain"

    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        "$productName با قیمت باورنکردنی $productPrice تومان فقط در فروشگاه زیر \n $url"
    )

    context.startActivity(Intent.createChooser(shareIntent, "Share to..."))

}