package com.rique.walksellerstreet.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rique.walksellerstreet.handler.LocationHandler
import com.rique.walksellerstreet.repository.ISellerRepository
import com.rique.walksellerstreet.ui.state.LocationSharingState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationSharingViewModel @Inject constructor(
    private val locationRepository: ISellerRepository,
    private val locationHandler: LocationHandler,
) : ViewModel() {
    private val _state: MutableState<LocationSharingState> = mutableStateOf(
        LocationSharingState()
    )

    val state: State<LocationSharingState>
        get() = _state

    private fun setIsSharingLocation(isSharingLocation: Boolean) {
        _state.value = _state.value.copy(isSharingLocation = isSharingLocation)
    }

    fun toggleLocationSharing() {
        if(_state.value.isSharingLocation){
            turnOffSharingLocation()
        } else {
            locationHandler.startLocationTracking { location ->
                locationRepository.updateSellerLocation("943eae44-b39f-4dd7-b631-4425d99a5457", location.latitude, location.longitude)
            }
            setIsSharingLocation(true)
        }
    }

    fun turnOffSharingLocation() {
        setIsSharingLocation(false)
        locationRepository.updateIsActiveSeller("943eae44-b39f-4dd7-b631-4425d99a5457", false)
    }
}