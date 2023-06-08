package com.farzin.newdigikala.ui.screens.basket

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import com.farzin.newdigikala.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.theme.*

@Composable
fun LoginOrRegisterSection(
    navController: NavController
){

    Card(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
            .clickable {navController.navigate(Screen.Profile.route) },
        shape = MaterialTheme.roundedShape.small,
        elevation = MaterialTheme.elevation.extraSmall ,
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ){

            Icon(
                painter = painterResource(id = R.drawable.import_96_orenge),
                contentDescription = "",
                tint = MaterialTheme.colors.amber,
                modifier = Modifier
                    .size(30.dp)
                    .weight(0.1f)
                    .align(Alignment.Top)
            )

            Spacer(modifier = Modifier.weight(0.05f))

            Column(
                modifier = Modifier.weight(0.8f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ){
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.login_or_register),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.darkText,
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.login_or_register_msg),
                    textAlign = TextAlign.Start,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.h6,
                )
            }

            Spacer(modifier = Modifier.weight(0.05f))

            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .size(22.dp)
                    .weight(0.1f)
                    .align(Alignment.Top)
                    .padding(top = MaterialTheme.spacing.small)

            )

        }
    }

}