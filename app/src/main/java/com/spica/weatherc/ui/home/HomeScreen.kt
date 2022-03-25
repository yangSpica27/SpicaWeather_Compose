package com.spica.weatherc.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.baidu.location.e.k.y
import com.spica.weatherc.R
import com.spica.weatherc.ui.navigation.NavScreen
import com.spica.weatherc.ui.widget.card.*


@Composable
fun HomeScreen(
    navController: NavController,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // 点击跳转到城市选择
                    navController.navigate(NavScreen.Cities.route)
                },
                backgroundColor = Color.Black,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        },
        isFloatingActionButtonDocked = true,
    ) {

        Column(
            Modifier
                .fillMaxSize()
        ) {
            TopBar(navController = navController, scrollState = scrollState)
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
                    .verticalScroll(scrollState)

            ) {
                Spacer(modifier = Modifier.height(20.dp))
                NowCard(weather = null)
                Spacer(modifier = Modifier.height(20.dp))
                HourlyCard(list = listOf())
                Spacer(modifier = Modifier.height(20.dp))
                DailyCard(list = listOf())
                Spacer(modifier = Modifier.height(20.dp))
                SunriseCard()
                Spacer(modifier = Modifier.height(20.dp))
                TipsCard()
                Spacer(modifier = Modifier.height(40.dp))
            }
        }


    }
}


@Composable
private fun TopBar(
    navController: NavController,
    scrollState: ScrollState
) {
    val target = LocalDensity.current.run {
        200.dp.toPx()
    }
    val scrollPercent: Float = if (scrollState.maxValue > target) {
        scrollState.value / target
    } else {
        1f
    }
    Column(
        Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            Modifier
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.navigate(NavScreen.Select.route)
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_menu), contentDescription = null)
            }
            Text(
                text = "中国，南京${scrollPercent}",
                modifier = Modifier
                    .weight(1f)
                    .alpha(1f - scrollPercent),
                fontStyle = MaterialTheme.typography.h2.fontStyle,
                textAlign = TextAlign.Center
            )
            IconButton(onClick = {
                navController.navigate(NavScreen.Setting.route)
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_tip), contentDescription = null)
            }
        }
        Divider(Modifier.height(1.dp))
    }
}