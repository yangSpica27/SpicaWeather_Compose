package com.spica.weatherc.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.spica.weatherc.ui.cities.CitiesScreen
import com.spica.weatherc.ui.detail.DetailScreen
import com.spica.weatherc.ui.home.HomeScreen
import com.spica.weatherc.ui.navigation.NavScreen
import com.spica.weatherc.ui.select.SelectScreen
import com.spica.weatherc.ui.setting.SettingScreen

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    ProvideWindowInsets {

        // 路由
        NavHost(
            navController = navController,
            // 默认进入的页面
            startDestination = NavScreen.Home.route
        ) {
            composable(NavScreen.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(NavScreen.Cities.route){
                CitiesScreen(navController = navController)
            }
            composable(NavScreen.ContentDetails.route){
                DetailScreen(navController = navController)
            }
            composable(NavScreen.Select.route){
                SelectScreen(navController = navController)
            }
           composable(NavScreen.Setting.route){
               SettingScreen()
           }
        }

    }
}
