package com.farzin.newdigikala.ui.screens.favorite_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun FavoriteListScreen(
    navController: NavController
) {

    Scaffold(
        topBar = {
            FavoriteTopBar(navController = navController)
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
            ) {
                FavoriteListSection(navController = navController)
            }
        }
    )

}