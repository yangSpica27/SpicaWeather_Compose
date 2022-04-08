package com.spica.weatherc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.spica.weatherc.ui.home.HomeScreen
import com.spica.weatherc.ui.main.MainScreen
import com.spica.weatherc.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AppTheme {
                MainScreen()
            }
        }
    }
}

