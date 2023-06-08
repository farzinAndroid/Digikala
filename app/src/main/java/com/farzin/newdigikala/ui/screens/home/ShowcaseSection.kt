package com.farzin.newdigikala.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.farzin.newdigikala.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.components.RoundedIconBox
import com.farzin.newdigikala.ui.theme.LocalSpacing
import com.farzin.newdigikala.ui.theme.amber
import com.farzin.newdigikala.ui.theme.grayCategory
import com.farzin.newdigikala.util.Constants.AUCTION_URL
import com.farzin.newdigikala.util.Constants.DIGIJET_URL
import com.farzin.newdigikala.util.Constants.DIGIPAY_URL
import com.farzin.newdigikala.util.Constants.DIGIPLUS_URL
import com.farzin.newdigikala.util.Constants.GIFT_CARD_URL
import com.farzin.newdigikala.util.Constants.MORE_URL
import com.farzin.newdigikala.util.Constants.PINDO_URL
import com.farzin.newdigikala.util.Constants.SHOPPING_URL

@Composable
fun ShowcaseSection(navController: NavController) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = LocalSpacing.current.semiMedium,
                vertical = LocalSpacing.current.biggerSmall
            )
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            RoundedIconBox(
                image = painterResource(id = R.drawable.digijet),
                title = stringResource(id = R.string.digikala_jet),
                onClick = onBoxClick(
                    navController = navController,
                    url = DIGIJET_URL
                ),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.auction),
                title = stringResource(id = R.string.digi_style),
                onClick = onBoxClick(
                    navController = navController,
                    url = AUCTION_URL
                ),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.digipay),
                title = stringResource(id = R.string.digi_pay),
                onClick = onBoxClick(
                    navController,
                    url = DIGIPAY_URL
                ),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.pindo),
                title = stringResource(id = R.string.pindo),
                bgColor = MaterialTheme.colors.amber,
                onClick = onBoxClick(
                    navController,
                    url = PINDO_URL
                ),
            )

        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            RoundedIconBox(
                image = painterResource(id = R.drawable.shopping),
                title = stringResource(id = R.string.digi_shopping),
                onClick = onBoxClick(
                    navController,
                    url = SHOPPING_URL
                ),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.giftcard),
                title = stringResource(id = R.string.gift_card),
                onClick = onBoxClick(
                    navController,
                    url = GIFT_CARD_URL
                ),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.digiplus),
                title = stringResource(id = R.string.digi_plus),
                onClick = onBoxClick(
                    navController,
                    url = DIGIPLUS_URL
                ),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.more),
                title = stringResource(id = R.string.more),
                bgColor = MaterialTheme.colors.grayCategory,
                onClick = onBoxClick(
                    navController,
                    url = MORE_URL
                ),
            )

        }

    }

}


@Composable
fun onBoxClick(navController: NavController, url: String): () -> Unit =
    { navController.navigate(route = Screen.WebView.route + "?url=${url}") }
