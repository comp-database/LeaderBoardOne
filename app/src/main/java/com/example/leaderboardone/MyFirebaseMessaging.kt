package com.example.leaderboardone

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "NotificationChannel"
const val channelName = "com.example.leaderboardone"

class MyFirebaseMessaging : FirebaseMessagingService() {
    private val firebaseMessaging = FirebaseMessaging.getInstance()

    override fun onNewToken(token: String) {
        Log.d( "Refreshed token:",token)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("recieve",remoteMessage.toString())
        if(remoteMessage.notification != null){
            generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun generateNotification(title : String, message: String){
        val intent = Intent(this,Login_screen::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        Log.d("FCMToken", "token "+ firebaseMessaging.token)
        val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT)

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.app_logo)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title, message))

//        FirebaseMessaging.getInstance().setDeliveryMetricsExportToBigQuery(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelId, channelName,
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0,builder.build())
    }

    private fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteView = RemoteViews("com.example.leaderboardone",R.layout.activity_notification)

        remoteView.setTextViewText(R.id.tv_notifi_title,title)
        remoteView.setTextViewText(R.id.tv_message_notify,message)
        remoteView.setImageViewResource(R.id.iv_notifi,R.drawable.app_logo)
        return remoteView
    }
}