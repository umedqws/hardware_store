package com.example.hardwarestore

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

class App:Application() {
        override fun onCreate() {
            super.onCreate()

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                crateNotificationChannel(notificationManager)
            }
        }
        @RequiresApi(Build.VERSION_CODES.O)
        private fun crateNotificationChannel(notificationManager: NotificationManager){
            val channel = NotificationChannel(
                PAYMENTS_CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = PAYMENTS_DESCRIPTION
                enableLights(true)
                lightColor = Color.GREEN
            }
            notificationManager.createNotificationChannel(channel)
        }
        companion object {
            const val PAYMENTS_CHANNEL_ID = "payments_notification_channel_id"
            const val CHANNEL_NAME = "Уведомления о покупке"
            const val PAYMENTS_DESCRIPTION = "Через этот канал вы получете уведомления о покупке"
        }

    }

