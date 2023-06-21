package com.farzin.newdigikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.data.model.basket.CartDetails
import com.farzin.newdigikala.ui.theme.DigikalaLightRed
import com.farzin.newdigikala.util.DigitHelper
import com.farzin.newdigikala.util.DigitHelper.digitByLangAndSeparator
import com.farzin.newdigikala.R


@Composable
fun CartPriceDetailSection(
    item: CartDetails,
    shippingCost:Int = 0
) {

    var title = stringResource(R.string.basket_summary)
    if (shippingCost > 0){
        title = stringResource(R.string.cost_details)
    }

    Column(
        modifier = Modifier.padding(
            start = MaterialTheme.spacing.medium,
            end = MaterialTheme.spacing.medium,
            top = MaterialTheme.spacing.medium,
            bottom = 120.dp
        )
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.darkText
            )

            Text(
                text = "${DigitHelper.digitByLangAndSeparator(item.totalCount.toString())} ${
                    stringResource(
                        R.string.goods
                    )
                }",
                style = MaterialTheme.typography.h6,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

        PriceRow(
            stringResource(id = R.string.goods_price),
            digitByLangAndSeparator(item.totalPrice.toString())
        )
        val discountPercent = (1 - item.payablePrice.toDouble() / item.totalPrice.toDouble()) * 100
        PriceRow(
            stringResource(id = R.string.goods_discount),
            digitByLangAndSeparator(item.totalDiscount.toString()),
            discountPercent.toInt()
        )
        PriceRow(
            stringResource(id = R.string.goods_total_price),
            digitByLangAndSeparator(item.payablePrice.toString())
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))


        if (shippingCost > 0){
            Divider(
                Modifier
                    .padding(
                        vertical = MaterialTheme.spacing.medium,
                        horizontal = MaterialTheme.spacing.small
                    )
                    .alpha(0.6f),
                color = Color.LightGray
            )

            PriceRow(
                stringResource(id = R.string.delivery_cost),
                digitByLangAndSeparator(shippingCost.toString())
            )

            DotTextRow(text = stringResource(R.string.shipping_cost_last_alert))

            PriceRow(
                stringResource(id = R.string.final_price),
                digitByLangAndSeparator((item.payablePrice + shippingCost).toString())
            )
        }else{
            stringResource(R.string.shipping_cost_alert)
        }





        Divider(
            Modifier
                .padding(
                    vertical = MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.small
                )
                .alpha(0.6f),
            color = Color.LightGray
        )
        DigiClubScore(item.payablePrice)

    }


}


@Composable
fun DotTextRow(text:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.dot_bullet),
            color = Color.Gray,
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            color = Color.Gray,
            modifier = Modifier.weight(1f)
        )
    }

}

@Composable
private fun DigiClubScore(
    payedPrice: Long
) {

    val score = payedPrice / 100_000

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painterResource(id = R.drawable.digi_score),
                contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
                    .padding(MaterialTheme.spacing.extraSmall)
            )
            Text(
                text = stringResource(id = R.string.digiclub_score),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
            )
        }

        Text(
            text = "${digitByLangAndSeparator(score.toString())} ${stringResource(id = R.string.score)}",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.darkText,
        )

    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerSmall))
    Text(
        text = stringResource(R.string.digiclub_description),
        style = MaterialTheme.typography.h6,
        color = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.biggerSmall)
    )


}

@Composable
private fun PriceRow(
    title: String,
    price: String,
    discount: Int = 0,
) {

    var color = MaterialTheme.colors.darkText
    var ourPrice = price
    if (discount > 0) {
        color = MaterialTheme.colors.DigikalaLightRed
        ourPrice = "(${digitByLangAndSeparator(discount.toString())}%) $price"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
        )

        Row {
            Text(
                text = ourPrice,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.SemiBold,
                color = color,
            )

            Icon(
                painter = painterResource(id = R.drawable.toman),
                contentDescription = "",
                tint = color,
                modifier = Modifier
                    .size(24.dp)
                    .padding(MaterialTheme.spacing.extraSmall)
            )
        }

    }
}