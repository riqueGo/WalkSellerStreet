package com.rique.walksellerstreet.di

import androidx.activity.ComponentActivity
import androidx.compose.runtime.staticCompositionLocalOf
import com.rique.walksellerstreet.ui.viewModel.LocationSharingViewModel

val LocalLocationSharingViewModel = staticCompositionLocalOf<LocationSharingViewModel> {
    error("No LocationSharingViewModel provided")
}

val LocalComponentActivity = staticCompositionLocalOf<ComponentActivity> {
    error("No ComponentActivity provideded")
}