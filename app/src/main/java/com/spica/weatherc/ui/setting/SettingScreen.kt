package com.spica.weatherc.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.spica.weatherc.ui.widget.CustomToolbar

/**
 * 设置页面
 */
@Composable
fun SettingScreen(navController: NavController) {

    Scaffold(
        topBar = {
            CustomToolbar(leftWidget = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
                rightWidget = { },
                title = {
                    Text(text = "设置", style = MaterialTheme.typography.h6)
                }
            )
        }
    ) {

        LazyColumn(modifier = Modifier.fillMaxWidth()) {

        }
    }
}