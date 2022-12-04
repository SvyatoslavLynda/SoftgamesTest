package com.example.softgamestest

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        private const val CLIENT_URL = "file:///android_asset/index.html"
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.apply {
            javaScriptEnabled = true
            useWideViewPort = true
        }
        webView.addJavascriptInterface(JSBridge(webView), JSBridge.WEB_INTERFACE_NAME)
        webView.loadUrl(CLIENT_URL)
    }
}