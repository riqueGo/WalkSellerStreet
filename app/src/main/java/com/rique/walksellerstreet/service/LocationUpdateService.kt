package com.rique.walksellerstreet.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.rique.walksellerstreet.handler.LocationHandler
import com.rique.walksellerstreet.repository.ISellerRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationUpdateService : Service() {
    @Inject
    lateinit var sellerRepository: ISellerRepository

    @Inject
    lateinit var locationHandler: LocationHandler

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("Service", "OnStartCommand")
        locationHandler.startLocationTracking { location ->
            sellerRepository.updateSellerLocation("943eae44-b39f-4dd7-b631-4425d99a5457", location.latitude, location.longitude)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        Log.i("Service", "OnDestroy")
        super.onDestroy()
        sellerRepository.updateIsActiveSeller("943eae44-b39f-4dd7-b631-4425d99a5457", false)
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.i("Service", "OnBind")
        TODO("Not yet implemented")
    }
}