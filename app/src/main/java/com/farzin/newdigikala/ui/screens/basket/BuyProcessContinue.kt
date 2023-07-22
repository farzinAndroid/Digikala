package com.farzin.newdigikala.ui.screens.basket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.R
import com.farzin.newdigikala.ui.theme.*
import com.farzin.newdigikala.util.DigitHelper

@Composable
fun BuyProcessContinue(
    price: Long,
    shippingCost: Int = 0,
    onClick: () -> Unit,
) {

    var title = stringResource(R.string.goods_total_price)
    if (shippingCost > 0) {
        title = stringResource(R.string.final_price)
    }


    Card(
        shape = MaterialTheme.roundedShape.extraSmall,
        elevation = MaterialTheme.elevation.extraSmall,
        border = BorderStroke(width = 1.dp, color = Color.LightGray.copy(0.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.semiMedium,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
                shape = MaterialTheme.roundedShape.small
            ) {
                Text(
                    text = stringResource(R.string.purchase_process),
                    color = Color.White,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.biggerMedium,
                            vertical = MaterialTheme.spacing.extraSmall,
                        )
                )
            }


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colors.semiDarkText,
                    style = MaterialTheme.typography.h6,
                )

                Row() {
                    Text(
                        text = DigitHelper.digitByLangAndSeparator((price + shippingCost).toString()),
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.SemiBold
                    )

                    Image(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        modifier = Modifier
                            .size(MaterialTheme.spacing.semiLarge)
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    )
                }

            }
        }
    }
}