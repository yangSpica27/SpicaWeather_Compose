package com.spica.weatherc.ui.widget.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.spica.weatherc.R
import com.spica.weatherc.common.WeatherCodeUtils
import com.spica.weatherc.common.getIconRes
import com.spica.weatherc.ui.theme.LightTextColor
import com.spica.weatherc.model.weather.Weather
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun NowCard(weather: Weather) {
    val sdf2 = SimpleDateFormat("更新于：HH:mm", Locale.CHINA)
    Card {
        Column(Modifier.fillMaxWidth()) {
            ConstraintLayout(
                Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(vertical = 12.dp, horizontal = 14.dp)
            ) {
                val (icon,
                    weatherName,
                    temp,
                    feelTemp,
                    updateTime,
                    air
                ) = createRefs()
                Image(
                    painter = painterResource(
                        id = WeatherCodeUtils.getWeatherCode(
                            weather
                                .todayWeather
                                .iconId.toString()
                        )
                            .getIconRes()
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .height(64.dp)
                        .constrainAs(icon) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                )

                // 温度
                Text(
                    text = "${weather.todayWeather.temp}℃",
                    fontStyle = MaterialTheme.typography.h3.fontStyle,
                    fontSize = MaterialTheme.typography.h3.fontSize,
                    color = Color.White,
                    modifier = Modifier.constrainAs(temp) {
                        top.linkTo(icon.top)
                        end.linkTo(parent.end)
                    }
                )
                // 天气
                Text(
                    text = weather.todayWeather.weatherName,
                    color = Color.White,
                    fontStyle = MaterialTheme.typography.body1.fontStyle,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    modifier = Modifier
                        .constrainAs(weatherName) {
                            top.linkTo(icon.bottom)
                            start.linkTo(parent.start)
                        }
                        .padding(vertical = 12.dp)
                )

                // air
                Text(
                    text = "空气质量：${weather.air.category}",
                    color = Color.White,
                    fontStyle = MaterialTheme.typography.body2.fontStyle,
                    fontSize = MaterialTheme.typography.body2.fontSize,
                    modifier = Modifier
                        .constrainAs(air) {
                            top.linkTo(weatherName.bottom)
                            start.linkTo(parent.start)
                        }
                )

                // 体感温度
                Text(
                    text = "体感温度：${weather.todayWeather.feelTemp}℃",
                    color = Color.White,
                    fontStyle = MaterialTheme.typography.body2.fontStyle,
                    fontSize = MaterialTheme.typography.body2.fontSize,
                    modifier = Modifier
                        .constrainAs(feelTemp) {
                            top.linkTo(weatherName.top)
                            bottom.linkTo(weatherName.bottom)
                            end.linkTo(parent.end)
                        }
                )

                // 更新时间
                Text(
                    text = sdf2.format(weather.todayWeather.obsTime),
                    color = Color.White,
                    fontStyle = MaterialTheme.typography.body2.fontStyle,
                    fontSize = MaterialTheme.typography.body2.fontSize,
                    modifier = Modifier
                        .constrainAs(updateTime) {
                            top.linkTo(air.top)
                            bottom.linkTo(air.bottom)
                            end.linkTo(parent.end)
                        }
                )

            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 14.dp, vertical = 12.dp)
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "风速",
                        fontStyle = MaterialTheme.typography.body2.fontStyle,
                        fontSize = MaterialTheme.typography.body2.fontSize,
                        color = LightTextColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${weather.todayWeather.windSpeed}km/h",
                        fontStyle = MaterialTheme.typography.h6.fontStyle,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "气压",
                        fontStyle = MaterialTheme.typography.body2.fontStyle,
                        fontSize = MaterialTheme.typography.body2.fontSize,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = LightTextColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${weather.todayWeather.windPa}pha",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontStyle = MaterialTheme.typography.h6.fontStyle,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "湿度",
                        fontStyle = MaterialTheme.typography.body2.fontStyle,
                        fontSize = MaterialTheme.typography.body2.fontSize,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Right,
                        color = LightTextColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${weather.todayWeather.water}%",
                        fontStyle = MaterialTheme.typography.h6.fontStyle,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Right
                    )
                }
            }
        }
    }
}