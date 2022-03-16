package com.spica.weatherc.ui.widget.card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spica.weatherc.R
import com.spica.weatherc.ui.theme.BgPathColor
import com.spica.weatherc.ui.theme.LightTextColor
import me.spica.weather.model.weather.HourlyWeatherBean


/**
 * Hourly Weather Card
 */
@Composable
fun HourlyCard(list: List<HourlyWeatherBean>) {
    val listData = (0..9).toList()
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "小时级别天气信息", fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 14.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow {
            items(listData) {
                Column(Modifier.width(70.dp)) {
                    Text(
                        text = "23h",
                        Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Canvas(modifier = Modifier
                        .height(100.dp)
                        .width(70.dp),
                        onDraw = {
                            val bgPath = Path()
                            bgPath.moveTo(0f, size.height / 2f)
                            bgPath.lineTo(size.width, size.height / 2f)
                            bgPath.lineTo(size.width, size.height)
                            bgPath.lineTo(0f, size.height)
                            bgPath.close()

                            drawPath(
                                path = bgPath,
                                style = Fill,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        BgPathColor,
                                        Color.Transparent
                                    ),
                                    start = Offset(0f, 0f),
                                    end = Offset(0f, size.height)
                                )
                            )

                            drawLine(
                                color = Color.LightGray,
                                start = Offset(0f, 50.dp.toPx()),
                                end = Offset(35.dp.toPx(), 50.dp.toPx()),
                                strokeWidth = 2.dp.toPx(),

                                )
                            drawLine(
                                color = Color.LightGray,
                                start = Offset(35f, 50.dp.toPx()),
                                end = Offset(70.dp.toPx(), 50.dp.toPx()),
                                strokeWidth = 2.dp.toPx()
                            )

                            drawLine(
                                color = Color.LightGray,
                                start = Offset(size.width / 2f, size.height / 2f),
                                end = Offset(size.width / 2f, size.height),
                                strokeWidth = 2.dp.toPx(),
                                pathEffect = PathEffect
                                    .dashPathEffect(
                                        floatArrayOf(
                                            4.dp.toPx(),
                                            2.dp.toPx()
                                        ), 0f
                                    )
                            )



                            drawCircle(
                                color = Color.DarkGray,
                                radius = 6.dp.toPx(),
                                center = Offset(x = 35.dp.toPx(), y = 50.dp.toPx())
                            )

                            drawCircle(
                                color = Color.White,
                                radius = 4.dp.toPx(),
                                center = Offset(x = 35.dp.toPx(), y = 50.dp.toPx())
                            )

                        })
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        Modifier.fillMaxWidth(),
                        Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_sunny),
                            contentDescription = null,
                            Modifier.width(48.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_umbrella),
                            contentDescription = null,
                            tint = LightTextColor
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "58%",
                            textAlign = TextAlign.Center,
                            color = LightTextColor,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}