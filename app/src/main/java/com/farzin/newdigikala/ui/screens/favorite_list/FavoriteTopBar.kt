package com.farzin.newdigikala.ui.screens.favorite_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.ui.theme.font_standard
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun FavoriteTopBar(navController: NavController) {

    TopAppBar(
        backgroundColor = Color.White,
        modifier = Modifier
            .height(70.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = MaterialTheme.spacing.small),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                Text(
                    text = stringResource(id = R.string.my_fav_list),
                    color = Color.DarkGray,
                    fontFamily = font_standard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(0.8f)
                )


            }
        }
    }

}