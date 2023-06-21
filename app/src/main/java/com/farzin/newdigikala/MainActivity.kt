package com.farzin.newdigikala

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import com.farzin.newdigikala.navigation.BottomNavigationBar
import com.farzin.newdigikala.navigation.SetupNavGraph
import com.farzin.newdigikala.ui.components.AppConfig
import com.farzin.newdigikala.ui.components.ChangeStatusBarColor
import com.farzin.newdigikala.ui.theme.DigikalaTheme
import com.farzin.newdigikala.util.Constants.ENGLISH_LANG
import com.farzin.newdigikala.util.Constants.USER_LANGUAGE
import com.farzin.newdigikala.util.LocaleUtils

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigikalaTheme {
                navController = rememberNavController()
                ChangeStatusBarColor(navController = navController)


                AppConfig()

                Log.e("TAG", USER_LANGUAGE)

                LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

                val direction = if (USER_LANGUAGE == ENGLISH_LANG) {
                    androidx.compose.ui.unit.LayoutDirection.Ltr
                } else {
                    androidx.compose.ui.unit.LayoutDirection.Rtl
                }
                CompositionLocalProvider(LocalLayoutDirection provides direction) {

                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                })
                        }
                    ) {
                        SetupNavGraph(navController = navController)
                    }

                }


            }
        }
    }
}
