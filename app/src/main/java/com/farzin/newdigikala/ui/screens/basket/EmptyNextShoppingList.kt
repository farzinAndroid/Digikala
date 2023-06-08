package com.farzin.newdigikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.farzin.newdigikala.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.semiDarkText
import com.farzin.newdigikala.util.Constants

@Composable
fun EmptyNextShoppingList() {

    val config = LocalConfiguration.current
    var verticalArrangement: Arrangement.Vertical = Arrangement.Center
    if (Constants.USER_TOKEN == "null") {
        verticalArrangement = Arrangement.Top
    }

    Column(
        modifier = Modifier
            .height(config.screenHeightDp.dp - 60.dp)
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = verticalArrangement
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_next_chart),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(180.dp)
                .width(200.dp)

        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.next_cart_list_is_empty),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.darkText,
        )

        Spacer(modifier = Modifier.height(10.dp))


        Text(
            text = stringResource(R.string.next_cart_list_is_empty_msg),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.semiDarkText,
            modifier = Modifier.fillMaxWidth(0.8f),
            textAlign = TextAlign.Center
        )


    }

}