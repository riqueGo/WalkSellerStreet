package com.rique.walksellerstreet

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.rique.walksellerstreet.ui.screen.LocationSharingScreen
import com.rique.walksellerstreet.ui.theme.WalkSellerStreetTheme
import com.rique.walksellerstreet.ui.viewModel.LocationSharingViewModel

class MainActivity : ComponentActivity() {
    private val locationSharingViewModel: LocationSharingViewModel by viewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            shareLocation()
        } else {
            finish()
        }
    }

    private fun askPermissions() = when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) -> {
            shareLocation()
        } else -> {
            requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
    }

    private fun shareLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        askPermissions()
        setContent {
            WalkSellerStreetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LocationSharingScreen(locationSharingViewModel)
                }
            }
        }
    }
}