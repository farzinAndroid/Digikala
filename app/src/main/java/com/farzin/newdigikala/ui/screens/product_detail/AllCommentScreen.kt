package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AllCommentScreen(
    navController: NavController,
    id: String,
) {


    Text(text = id)
}