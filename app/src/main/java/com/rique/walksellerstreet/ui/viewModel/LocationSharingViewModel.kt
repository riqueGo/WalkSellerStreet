package com.rique.walksellerstreet.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rique.walksellerstreet.ui.state.LocationSharingState

class LocationSharingViewModel : ViewModel() {
    private val _state: MutableState<LocationSharingState> = mutableStateOf(
        LocationSharingState()
    )

    val state: State<LocationSharingState>
        get() = _state

    fun toggleLocationSharing() {
        _state.value = _state.value.copy(isSharingLocation = !_state.value.isSharingLocation)
    }
}