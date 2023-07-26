package com.farzin.newdigikala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.farzin.newdigikala.ui.screens.basket.BasketScreen
import com.farzin.newdigikala.ui.screens.category.CategoryScreen
import com.farzin.newdigikala.ui.screens.checkout.CheckoutScreen
import com.farzin.newdigikala.ui.screens.home.HomeScreen
import com.farzin.newdigikala.ui.screens.home.WebPageScreen
import com.farzin.newdigikala.ui.screens.product_detail.AllCommentScreen
import com.farzin.newdigikala.ui.screens.product_detail.ProductDescription
import com.farzin.newdigikala.ui.screens.product_detail.ProductDetailScreen
import com.farzin.newdigikala.ui.screens.product_detail.ProductTechnicalFeaturesScreen
import com.farzin.newdigikala.ui.screens.product_detail.SetNewCommentScreen
import com.farzin.newdigikala.ui.screens.profile.ProfileScreen
import com.farzin.newdigikala.ui.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navController)
        }

        composable(route = Screen.Basket.route) {
            BasketScreen(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = Screen.Checkout.route) {
            CheckoutScreen(navController = navController)
        }



        composable(
            route = Screen.WebView.route + "?url={url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) {
            val url = it.arguments?.getString("url")
            url?.let {
                WebPageScreen(navController = navController, url = url)
            }
        }


        composable(
            route = Screen.ProductDetail.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = ""
                }
            )
        ) {

            it.arguments?.getString("id")?.let { id ->
                ProductDetailScreen(navController = navController, id = id)
            }


        }


        composable(route = Screen.ProductDescription.route + "/{description}",
            arguments = listOf(
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )) {

            it.arguments?.getString("description")?.let { description ->
                ProductDescription(
                    navController = navController,
                    desc = description
                )
            }
        }


        composable(route = Screen.TechnicalFeatures.route + "/{jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )) {

            it.arguments?.getString("jsonString")?.let { jsonString ->
                ProductTechnicalFeaturesScreen(
                    navController = navController,
                    jsonString = jsonString
                )
            }
        }


        composable(route = Screen.SetNewComment.route + "?productId={productId}?productName={productName}?imageUrl={imageUrl}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = " "
                },
                navArgument("productName") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = " "
                },
                navArgument("imageUrl") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = " "
                }
            )
        ) {

            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("productName")?.let { productName ->
                    it.arguments!!.getString("imageUrl")?.let { imageUrl ->
                        SetNewCommentScreen(
                            navController = navController,
                            productName = productName,
                            productId = productId,
                            imageUrl = imageUrl
                        )
                    }
                }


            }


        }


        composable(
            route = Screen.AllComment.route + "/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = ""
                }
            )
        ) {

            it.arguments?.getString("productId")?.let { id ->
                AllCommentScreen(navController = navController, id = id)
            }


        }


    }
}