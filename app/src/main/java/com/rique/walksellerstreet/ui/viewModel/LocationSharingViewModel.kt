package com.rique.walksellerstreet.ui.viewModel

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rique.walksellerstreet.service.LocationUpdateService
import com.rique.walksellerstreet.ui.state.LocationSharingState

class LocationSharingViewModel : ViewModel() {
    private val _state: MutableState<LocationSharingState> = mutableStateOf(
        LocationSharingState()
    )

    val state: State<LocationSharingState>
        get() = _state

    private fun setIsSharingLocation(isSharingLocation: Boolean) {
        _state.value = _state.value.copy(isSharingLocation = isSharingLocation)
    }

    fun toggleLocationSharing(context: Context) {
        if(_state.value.isSharingLocation){
            setIsSharingLocation(false)
            context.stopService(Intent(context, LocationUpdateService::class.java))
        } else {
            setIsSharingLocation(true)
            context.startService(Intent(context, LocationUpdateService::class.java))
        }
    }
}