package com.rique.walksellerstreet.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.rique.walksellerstreet.MainActivity
import com.rique.walksellerstreet.R
import com.rique.walksellerstreet.handler.LocationHandler
import com.rique.walksellerstreet.repository.ISellerRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LocationUpdateService : Service() {
    private val CHANNEL_ID = "WalkSellerServiceChannel"

    @Inject
    lateinit var sellerRepository: ISellerRepository

    @Inject
    lateinit var locationHandler: LocationHandler

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("Service", "OnStartCommand")

        val input = intent!!.getStringExtra("inputExtra")
        createNotificationChannel()

        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.action = "NOTIFICATION_CLICKED"
        notificationIntent.putExtra("ACTION", "RESUME_PREVIOUS_STATE")
        notificationIntent.putExtra("IS_SHARING_LOCATION", true)

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("WalkSeller Service")
            .setContentText(input)
            .setSmallIcon(R.drawable.my_location_24)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        locationHandler.startLocationTracking { location ->
            sellerRepository.updateSellerLocation("943eae44-b39f-4dd7-b631-4425d99a5457", location.latitude, location.longitude)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        Log.i("Service", "OnDestroy")
        sellerRepository.updateIsActiveSeller("943eae44-b39f-4dd7-b631-4425d99a5457", false)
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.i("Service", "OnBind")
        TODO("Not yet implemented")
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            "WalkSeller Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(
            NotificationManager::class.java
        )
        manager.createNotificationChannel(serviceChannel)
    }
}