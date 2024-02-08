package com.sandymist.hellocompose

import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import timber.log.Timber

class AppWebViewClient(val onPage: (String?) -> Unit) : WebViewClient() {
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?,
    ) {
        super.onReceivedError(view, request, error)
        Timber.e("++++ onReceivedError: $error")
    }

    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?,
    ) {
        super.onReceivedHttpError(view, request, errorResponse)
        Timber.e("++++ onReceivedHttpError: request ${request?.method} ${request?.url}")
        Timber.e("++++ onReceivedHttpError: status code ${errorResponse?.statusCode}")
        Timber.e("++++ onReceivedHttpError: reason ${errorResponse?.reasonPhrase}")
        Timber.e("++++ onReceivedHttpError: resp hdrs ${errorResponse?.responseHeaders}")
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        super.onReceivedSslError(view, handler, error)
        Timber.e("++++ onReceivedSslError: $error")
    }

    override fun onLoadResource(view: WebView?, url: String?) {
        super.onLoadResource(view, url)
        Timber.e("++++ onLoadResource: $url")
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Timber.e("++++ onPageStarted: $url")
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        onPage(url)
    }
}
