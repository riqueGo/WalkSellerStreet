package com.rique.walksellerstreet.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@SuppressLint("PrivateResource")
@Composable
fun Home() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val tertiaryColor = MaterialTheme.colorScheme.tertiary

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            DownloadScreen()
        },
        content = {
            Column {
                IconButton(
                    onClick = {
                        scope.launch { drawerState.open() }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = androidx.appcompat.R.drawable.abc_ic_menu_selectall_mtrl_alpha),
                        contentDescription = null,
                        tint = tertiaryColor
                    )
                }
                LocationSharingScreen()
            }
        }
    )
}