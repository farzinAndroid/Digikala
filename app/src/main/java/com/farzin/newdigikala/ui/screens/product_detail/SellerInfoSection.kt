package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.R
import com.farzin.newdigikala.ui.screens.basket.DetailRow
import com.farzin.newdigikala.ui.theme.DarkCyan
import com.farzin.newdigikala.ui.theme.DigikalaLightGreen
import com.farzin.newdigikala.ui.theme.DigikalaLightRed
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.extraSmall
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.semiDarkText
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.ui.theme.unSelectedBottomBar
import com.farzin.newdigikala.util.DigitHelper

@Composable
fun SellerInfoSection() {


    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                vertical = MaterialTheme.spacing.small,
                horizontal = MaterialTheme.spacing.semiMedium
            )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(R.string.seller),
                style = Typography.h5,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText
            )

        }


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Row(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.digi_logo),
                contentDescription = "",
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.small)
                    .size(25.dp)
                    .clip(MaterialTheme.roundedShape.small)
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            Column {

                Text(
                    text = stringResource(R.string.digikala),
                    style = Typography.h5,
                    color = MaterialTheme.colors.darkText
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "${DigitHelper.digitByLang("101")}% رضایت خریداران معتبر",
                        style = Typography.h6,
                        color = MaterialTheme.colors.semiDarkText
                    )

                }


                Divider(
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.small)
                        .fillMaxWidth()
                        .height(1.dp)
                        .alpha(0.4f)
                        .shadow(2.dp),
                    color = Color.LightGray,
                )

            }

        }


        Row(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.guarantee),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .clip(MaterialTheme.roundedShape.small)
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            Column {

                Text(
                    text = stringResource(R.string.warranty),
                    style = Typography.h6,
                    color = MaterialTheme.colors.darkText,
                    fontWeight = FontWeight.Bold
                )

                Divider(
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.small)
                        .fillMaxWidth()
                        .height(1.dp)
                        .alpha(0.4f)
                        .shadow(2.dp),
                    color = Color.LightGray,
                )

            }

        }

        Row(
            modifier = Modifier
                .padding(MaterialTheme.spacing.biggerSmall)
        ) {
            Column(
                modifier = Modifier
                    .width(16.dp)
                    .fillMaxHeight()
                    .padding(
                        vertical = MaterialTheme.spacing.small,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.in_stock),
                    contentDescription = "",
                    modifier = Modifier
                        .size(16.dp),
                    tint = MaterialTheme.colors.DarkCyan
                )

                Divider(
                    Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .alpha(0.6f),
                    color = Color.LightGray
                )

                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "",
                    modifier = Modifier
                        .size(10.dp)
                        .padding(1.dp),
                    tint = MaterialTheme.colors.DarkCyan,
                )

                Divider(
                    Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .alpha(0.6f),
                    color = Color.LightGray
                )

                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "",
                    modifier = Modifier
                        .size(10.dp)
                        .padding(1.dp),
                    tint = MaterialTheme.colors.DarkCyan,
                )


            }

            Column(Modifier.padding(horizontal = MaterialTheme.spacing.biggerMedium)) {

                Text(
                    text = stringResource(id = R.string.in_digi_stock),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.semiDarkText,
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.extraSmall),
                )

                DetailRow(
                    painterResource(id = R.drawable.k1),
                    stringResource(id = R.string.digikala_send),
                    color = MaterialTheme.colors.DigikalaLightRed,
                    fontStyle = MaterialTheme.typography.extraSmall
                )

                DetailRow(
                    painterResource(id = R.drawable.k2),
                    stringResource(id = R.string.fast_send),
                    color = MaterialTheme.colors.DigikalaLightGreen,
                    fontStyle = MaterialTheme.typography.extraSmall
                )

            }
        }


        Divider(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.small)
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f)
                .shadow(2.dp),
            color = Color.LightGray,
        )



        Row(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.digi_score),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .clip(MaterialTheme.roundedShape.small)
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            Column {

                Text(
                    text = stringResource(R.string.digiclub_get_score),
                    style = Typography.h6,
                    color = MaterialTheme.colors.darkText,
                )

                Divider(
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.small)
                        .fillMaxWidth()
                        .height(1.dp)
                        .alpha(0.4f)
                        .shadow(2.dp),
                    color = Color.LightGray,
                )

            }

        }


        Row(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.info),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .clip(MaterialTheme.roundedShape.small)
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            Column {

                Text(
                    text = "${stringResource(R.string.manufacturer_price)} 111 ${stringResource(R.string.toman)}",
                    style = Typography.h6,
                    color = MaterialTheme.colors.darkText,
                )

            }

        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.extraSmall
                ),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(
                text = stringResource(R.string.better_price_suggestion),
                style = Typography.extraSmall,
                color = MaterialTheme.colors.unSelectedBottomBar,
                modifier = Modifier
                    .clickable {  }
            )

            Image(
                painter = painterResource(R.drawable.mark),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)

            )

        }

    }

}