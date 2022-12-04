package com.example.softgamestest

import android.webkit.JavascriptInterface
import android.webkit.WebView

class JSBridge(private var webView: WebView) {

    companion object {
        const val WEB_INTERFACE_NAME = "JSBridge"
    }

    @JavascriptInterface
    fun makeFullName(firstName: String?, lastName: String?) {
        webView.post {
            val response = FormService.makeFullName(firstName, lastName)

            webView.evaluateJavascript("javascript:setFullName(\"$response\")", null)
        }
    }

    @JavascriptInterface
    fun calculateUserAge(dateOfBirthday: String?) {
        webView.postDelayed(
            {
                val date = FormDataParser.tryParseDate(dateOfBirthday)
                val response = FormService.calculateUserAge(date)

                webView.evaluateJavascript("javascript:setAge(\"$response\")", null)
            },
            5000,
        )
    }
}