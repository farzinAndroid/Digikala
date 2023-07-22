package com.farzin.newdigikala.ui.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import com.farzin.newdigikala.R
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.searchBarBg

@Composable
fun CheckoutTopBarSection(
    navController: NavHostController,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .clickable {
                    navController.popBackStack()
                }
                .size(24.dp)

        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.address_and_time),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.darkText,
        )

    }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(3.dp)
            .background(MaterialTheme.colors.searchBarBg)
    )

}