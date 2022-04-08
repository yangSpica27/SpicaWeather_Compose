package com.spica.weatherc.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spica.weatherc.R
import com.spica.weatherc.ui.navigation.NavScreen
import com.spica.weatherc.ui.widget.card.*
import com.spica.weatherc.viewmodel.CityViewModel
import com.spica.weatherc.viewmodel.WeatherViewModel


@Composable
fun HomeScreen(
    navController: NavController,
) {
    val viewModel: WeatherViewModel = hiltViewModel()
    val cityViewModel: CityViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    val weatherState =
        viewModel.weatherFlow.collectAsState(initial = null)
    viewModel.changeCity(cityViewModel.getAllCity().first())
    Scaffold(
        topBar = {
            TopBar(navController = navController)
        },
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

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp)
            ) {
                with(weatherState.value) {
                    if (this@with != null) {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .verticalScroll(scrollState)
                        ) {
                            Spacer(modifier = Modifier.height(20.dp))
                            NowCard(weather = this@with)
                            Spacer(modifier = Modifier.height(12.dp))
                            HourlyCard(list = hourlyWeather)
                            Spacer(modifier = Modifier.height(12.dp))
                            DailyCard(list = dailyWeather)
                            Spacer(modifier = Modifier.height(12.dp))
                            SunriseCard()
                            Spacer(modifier = Modifier.height(12.dp))
                            TipsCard(weather = this@with)
                            Spacer(modifier = Modifier.height(40.dp))

                        }
                    }

                }
            }


            AnimatedVisibility(visible = weatherState.value == null) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(12.dp))
                    TextButton(onClick = {
                        viewModel.changeCity(cityViewModel.getAllCity().first())
                    }) {
                        Text(text = "重试")
                    }
                }
            }

        }


    }
}


@Composable
private fun TopBar(
    navController: NavController,
) {
    Column(
        Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            Modifier
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.navigate(NavScreen.Select.route)
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_menu), contentDescription = null)
            }
            Text(
                text = "中国，南京",
                modifier = Modifier
                    .weight(1f),
                fontStyle = MaterialTheme.typography.h2.fontStyle,
                textAlign = TextAlign.Center
            )
            IconButton(onClick = {
                navController.navigate(NavScreen.Setting.route)
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_tip), contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider(Modifier.height(1.dp))
    }
}