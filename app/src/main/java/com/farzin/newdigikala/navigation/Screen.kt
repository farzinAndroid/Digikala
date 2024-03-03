package com.farzin.newdigikala.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash_screen")
    data object Home : Screen("home_screen")
    data object Category : Screen("category_screen")
    data object Basket : Screen("basket_screen")
    data object Profile : Screen("profile_screen")
    data object Checkout : Screen("checkout_screen")
    data object WebView : Screen("webView_screen")
    data object ProductDetail : Screen("product_detail_screen")
    data object ProductDescription : Screen("product_description_screen")
    data object TechnicalFeatures : Screen("product_technical_features_screen")
    data object SetNewComment : Screen("set_new_comment_screen")
    data object AllComment : Screen("all_comment_screen")
    data object Chart : Screen("chart_screen")
    data object Settings : Screen("settings_screen")
    data object UserAccount : Screen("user_account_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }

}