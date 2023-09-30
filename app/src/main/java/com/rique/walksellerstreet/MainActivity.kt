package com.rique.walksellerstreet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.rique.walksellerstreet.di.LocalComponentActivity
import com.rique.walksellerstreet.di.LocalLocationSharingViewModel
import com.rique.walksellerstreet.ui.screen.Home
import com.rique.walksellerstreet.ui.theme.WalkSellerStreetTheme
import com.rique.walksellerstreet.ui.viewModel.LocationSharingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val locationSharingViewModel: LocationSharingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalkSellerStreetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositionLocalProvider(
                        LocalComponentActivity provides this,
                        LocalLocationSharingViewModel provides locationSharingViewModel
                    ) {
                        Home()
                    }
                }
            }
        }
    }
}