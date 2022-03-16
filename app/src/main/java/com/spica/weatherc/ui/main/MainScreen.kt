package com.spica.weatherc.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.spica.weatherc.ui.home.HomeScreen
import com.spica.weatherc.ui.navigation.NavScreen

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    ProvideWindowInsets {

        NavHost(
            navController = navController,
            startDestination = NavScreen.Home.route
        ) {
            composable(NavScreen.Home.route) {
                HomeScreen(navController = navController)
            }
        }

    }
}
