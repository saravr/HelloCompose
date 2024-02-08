package com.sandymist.hellocompose.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.sandymist.hellocompose.AppWebViewClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebScreen(
    url: String,
    navController: NavController,
    onBackPressed: ((String) -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    val backEnabled by remember { mutableStateOf(true) }
    var loadedPage: String? by remember { mutableStateOf(null) }
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = AppWebViewClient {
                loadedPage = it
            }

            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.setSupportZoom(true)
        }
    }

    val prevRoute = navController.previousBackStackEntry?.destination?.route ?: "None"
    Timber.e("++++ WEB PREV ROUTE: $prevRoute")

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { webView },
        update = {
            it.loadUrl(url)
        },
    )

    BackHandler(enabled = backEnabled) {
        if (loadedPage.toString() == url) {
            onBackPressed?.let {
                scope.launch {
                    delay(500)
                    it("navA")
                }
            } ?: run {
                navController.popBackStack()
            }
        } else {
            webView.goBack()
        }
    }
}
