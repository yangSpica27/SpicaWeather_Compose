package com.spica.weatherc.ui.select

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.spica.weatherc.ui.widget.CustomToolbar


/**
 * 选择城市的页面
 */
@Composable
fun SelectScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomToolbar(leftWidget = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
                rightWidget = { },
                title = {
                    Text(text = "城市选择", style = MaterialTheme.typography.h6)
                }
            )
        }
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

        }
    }
}