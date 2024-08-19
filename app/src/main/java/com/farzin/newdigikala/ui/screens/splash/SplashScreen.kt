package com.farzin.newdigikala.ui.screens.splash

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.farzin.newdigikala.R
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.farzin.newdigikala.data.remote.CheckConnection
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.components.Loading3Dots
import com.farzin.newdigikala.ui.theme.splashBg
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current
    val isNetworkAvailable = remember {
        CheckConnection.isNetworkAvailable(context)
    }


    Splash(
        isNetworkAvailable=isNetworkAvailable,
        onRetryClicked = {
            if(CheckConnection.isNetworkAvailable(context)){
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            }else{
                Toast.makeText(
                    context,
                    context.getString(R.string.check_net),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    )
    LaunchedEffect(true) {
        delay(2500)
        if (isNetworkAvailable){
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        }
    }
}


@Composable
fun Splash(isNetworkAvailable:Boolean,onRetryClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.splashBg)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.digi_logo),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(100.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                modifier = Modifier.height(30.dp),
                painter = painterResource(id = R.drawable.digi_txt_white),
                contentDescription = null
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (isNetworkAvailable){
                Loading3Dots(false)
            }else{
                ReTry(
                    onRetryClicked = onRetryClicked
                )
            }
        }
    }
}