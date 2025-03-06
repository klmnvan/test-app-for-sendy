package com.example.sendyapp.presentation.screens.auth.components

import android.graphics.Color
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog

@Composable
fun OpenDialogOffer(textHtml: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() } ) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                }
            },
            update = { webView ->
                webView.setBackgroundColor(Color.WHITE)
                webView.loadData(textHtml, "text/html; charset=utf-8", "UTF-8")
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}