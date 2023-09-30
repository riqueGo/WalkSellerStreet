package com.rique.walksellerstreet.ui.screen

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.rique.walksellerstreet.R
import com.rique.walksellerstreet.di.LocalComponentActivity
import com.rique.walksellerstreet.di.LocalLocationSharingViewModel

@Composable
fun LocationSharingScreen() {
    val activity = LocalComponentActivity.current
    val viewModel = LocalLocationSharingViewModel.current
    val context = LocalContext.current

    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val isSharingLocation = viewModel.state.value.isSharingLocation

    // Initialize the launcher for requesting permissions
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted, update the state and start location sharing
            viewModel.toggleLocationSharing(context)
        }
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (message, button) = createRefs()

        Text(text = if (isSharingLocation) stringResource(R.string.sharing_location_started)
        else stringResource(R.string.sharing_location_stopped),
            color = tertiaryColor,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(message) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(32.dp)
                .fillMaxWidth())

        Button(onClick = {
            // Check for location permission before toggling location sharing
            val permission = Manifest.permission.ACCESS_FINE_LOCATION
            val granted = PackageManager.PERMISSION_GRANTED
            val isPermissionGranted =
                ActivityCompat.checkSelfPermission(activity, permission) == granted

            if (isPermissionGranted) {
                // Permission is already granted, update the state and start location sharing
                viewModel.toggleLocationSharing(context)
            } else {
                // Request permission using the launcher
                launcher.launch(permission)
            }
        }, shape = CircleShape, colors = ButtonDefaults.buttonColors(
            containerColor = primaryColor, // Use primary color
            contentColor = tertiaryColor
        ), modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        primaryColor, // Use primary color
                        secondaryColor // Use secondary color
                    )
                ), shape = CircleShape
            )
            .size(200.dp)
            .constrainAs(button) {
                top.linkTo(message.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Text(
                text = if (isSharingLocation) "Stop" else "Start", fontSize = 20.sp
            )
        }
    }
}