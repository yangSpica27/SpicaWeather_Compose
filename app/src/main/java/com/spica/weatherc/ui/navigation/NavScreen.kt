package com.spica.weatherc.ui.navigation

import androidx.compose.runtime.Immutable

/**
 * 页面
 */
@Immutable
sealed class NavScreen(val route: String) {

    // 主页
    object Home : NavScreen("Home")

    // 内容详情页面
    object ContentDetails : NavScreen("Details")

    // 城市列表
    object Cities : NavScreen("Cities")

    // 地点选择页面
    object Select : NavScreen("Select")


}