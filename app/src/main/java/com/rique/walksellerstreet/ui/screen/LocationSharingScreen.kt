package com.rique.walksellerstreet.ui.screen

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.rique.walksellerstreet.ui.viewModel.LocationSharingViewModel

@Composable
fun LocationSharingScreen(
    viewModel: LocationSharingViewModel
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val isSharingLocation = viewModel.state.value.isSharingLocation

    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (message, button) = createRefs()

        Text(
            text = if (isSharingLocation) "Click the button to stop sharing your location" else "Click the button to start sharing your location",
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
                .fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.toggleLocationSharing()
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor, // Use primary color
                contentColor = tertiaryColor
            ),
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            primaryColor, // Use primary color
                            secondaryColor // Use secondary color
                        )
                    ),
                    shape = CircleShape
                )
                .size(200.dp)
                .constrainAs(button) {
                    top.linkTo(message.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = if (isSharingLocation) "Stop" else "Start",
                fontSize = 20.sp
            )
        }
    }
}