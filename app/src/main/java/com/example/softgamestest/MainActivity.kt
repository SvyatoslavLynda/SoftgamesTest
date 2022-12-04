package com.example.softgamestest

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val CLIENT_URL = "file:///android_asset/index.html"
        private const val CHANNEL_ID = "SOFT_GAME_CHANNEL_ID"
        private const val CHANNEL_NAME = "SoftGames channel"
        private const val PUSH_NOTIFICATION_ID = 1
    }

    private val pushNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.apply {
            javaScriptEnabled = true
            useWideViewPort = true
        }
        webView.addJavascriptInterface(JSBridge(webView, this::showPush), JSBridge.WEB_INTERFACE_NAME)
        webView.loadUrl(CLIENT_URL)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pushNotificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun showPush() {
        finishAndRemoveTask()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                buildPush(
                    title = getString(R.string.push_title),
                    body = getString(R.string.push_body)
                )
            },
            2000,
        )
    }

    private fun buildPush(title: String, body: String) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntentFlag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
            else PendingIntent.FLAG_UPDATE_CURRENT
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, pendingIntentFlag)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(pendingIntent)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).apply {
                createNotificationChannel(
                    NotificationChannel(
                        CHANNEL_ID,
                        CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_HIGH
                    )
                )
                notify(PUSH_NOTIFICATION_ID, notificationBuilder.build())
            }
        } else {
            NotificationManagerCompat.from(this).apply {
                createNotificationChannel(
                    NotificationChannelCompat.Builder(
                        CHANNEL_ID,
                        NotificationManagerCompat.IMPORTANCE_HIGH
                    ).build()
                )
                notify(PUSH_NOTIFICATION_ID, notificationBuilder.build())
            }
        }
    }
}