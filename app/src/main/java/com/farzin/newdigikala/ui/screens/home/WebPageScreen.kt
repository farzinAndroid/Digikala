package com.farzin.newdigikala.ui.screens.home

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebPageScreen(
    navController: NavController,
    url: String
) {

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.userAgentString = System.getProperty("http.agent")
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })

}