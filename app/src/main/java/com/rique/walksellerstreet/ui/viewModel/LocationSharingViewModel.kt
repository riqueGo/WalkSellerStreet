package com.rique.walksellerstreet.ui.viewModel

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.rique.walksellerstreet.service.LocationUpdateService
import com.rique.walksellerstreet.ui.state.LocationSharingState


class LocationSharingViewModel : ViewModel() {
    private val _state: MutableState<LocationSharingState> = mutableStateOf(
        LocationSharingState()
    )

    val state: State<LocationSharingState>
        get() = _state

    fun setIsSharingLocation(isSharingLocation: Boolean) {
        _state.value = _state.value.copy(isSharingLocation = isSharingLocation)
    }

    fun toggleLocationSharing(context: Context) {
        if(_state.value.isSharingLocation){
            setIsSharingLocation(false)
            val serviceIntent = Intent(context, LocationUpdateService::class.java)
            context.stopService(serviceIntent)
        } else {
            setIsSharingLocation(true)
            val serviceIntent = Intent(context, LocationUpdateService::class.java)
            serviceIntent.putExtra("inputExtra", "Estamos atualizando sua localização")
            ContextCompat.startForegroundService(context, serviceIntent)
        }
    }
}