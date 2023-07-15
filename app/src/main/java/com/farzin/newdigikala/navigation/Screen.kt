package com.farzin.newdigikala.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Category : Screen("category_screen")
    object Basket : Screen("basket_screen")
    object Profile : Screen("profile_screen")
    object Checkout : Screen("checkout_screen")
    object WebView : Screen("webView_screen")
    object ProductDetail : Screen("product_detail_screen")

    fun withArgs(vararg args : Any) : String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }

}