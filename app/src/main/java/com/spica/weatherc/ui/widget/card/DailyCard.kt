package com.spica.weatherc.ui.widget.card

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.spica.weatherc.R
import com.spica.weatherc.ui.theme.BgPathColor
import me.spica.weather.model.weather.DailyWeatherBean


@Composable
fun DailyCard(list: List<DailyWeatherBean>) {
    val sortList = list.toList()
    val maxMaxTemp = sortList.maxByOrNull { it.maxTemp }?.maxTemp ?: 0
    val minMaxTemp = sortList.minByOrNull { it.maxTemp }?.maxTemp ?: 0

    val maxMinTemp = sortList.maxByOrNull { it.minTemp }?.minTemp ?: 0
    val minMinTemp = sortList.minByOrNull { it.minTemp }?.minTemp ?: 0



    Card {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "天级别天气信息", fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 14.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow {
                items(list.size) {
                    Column() {
                        DailyWeatherMaxTempChartItem(
                            maxMaxTemp = maxMaxTemp,
                            minMaxTemp = minMaxTemp,
                            currentMaxTemp = list[it].maxTemp,
                            lastMaxTemp = if (it != 0) {
                                list[it - 1].maxTemp
                            } else {
                                null
                            },
                            nextMaxTemp = if (it < list.size - 1) {
                                list[it + 1].maxTemp
                            } else {
                                null
                            }
                        )
                        DailyWeatherMinTempChartItem(
                            maxMinTemp = maxMinTemp,
                            minMinTemp = minMinTemp,
                            currentMinTemp = list[it].minTemp,
                            lastMinTep = if (it != 0) {
                                list[it - 1].minTemp
                            } else {
                                null
                            },
                            nextMinTemp = if (it < list.size - 1) {
                                list[it + 1].minTemp
                            } else {
                                null
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }

}


/**
 *
 */
@Composable
fun DailyWeatherMaxTempChartItem(
    maxMaxTemp: Int,
    minMaxTemp: Int,
    currentMaxTemp: Int,
    lastMaxTemp: Int?,
    nextMaxTemp: Int?
) {

    val value = animateIntAsState(targetValue = currentMaxTemp)


    val blurLinePaint = Paint().asFrameworkPaint().apply {
        strokeWidth = 6f
        color = ContextCompat.getColor(LocalContext.current, R.color.textColorPrimaryHintLight)
        maskFilter = BlurMaskFilter(4f, BlurMaskFilter.Blur.SOLID)
    }

    Canvas(
        modifier = Modifier
            .width(70.dp)
            .height(50.dp),
        onDraw = {

            blurLinePaint.maskFilter = BlurMaskFilter(
                4.dp.toPx(),
                BlurMaskFilter.Blur.SOLID
            )

            val paddingTop = 12.dp.toPx()

            val paddingBottom = 20.dp.toPx()

            val offsetCenter = Offset(
                size.width / 2f,
//                    size.height/2f
                (size.height - paddingBottom) -
                        (size.height - paddingTop - paddingBottom) * ((value.value - minMaxTemp) * 1f / maxMaxTemp)
            )

            lastMaxTemp?.let {
                val lastCenterHeight =
                    (size.height - paddingBottom) -
                            (size.height - paddingTop - paddingBottom) *
                            ((it - minMaxTemp) * 1f / maxMaxTemp)
                val offsetLeft = Offset(
                    0f,
                    lastCenterHeight / 2 +
                            offsetCenter.y / 2
                )

                val bgPath = Path()
                bgPath.moveTo(offsetLeft.x, offsetLeft.y)
                bgPath.lineTo(offsetLeft.x, size.height)
                bgPath.lineTo(offsetCenter.x, size.height)
                bgPath.lineTo(offsetCenter.x, offsetCenter.y)
                bgPath.close()

                // 绘制背景
                drawPath(
                    path = bgPath,
                    style = Fill,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            BgPathColor,
                            BgPathColor
                        )
                    )
                )

                // 绘制线
                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawLine(
                        offsetCenter.x, offsetCenter.y,
                        offsetLeft.x, offsetLeft.y,
                        blurLinePaint
                    )
                }

            }



            nextMaxTemp?.let {
                val nextCenterHeight =
                    (size.height - paddingBottom) -
                            (size.height - paddingTop - paddingBottom) *
                            ((it - minMaxTemp) * 1f / maxMaxTemp)
                val offsetRight = Offset(
                    size.width,
                    nextCenterHeight / 2 +
                            offsetCenter.y / 2
                )

                val bgPath = Path()
                bgPath.moveTo(offsetRight.x, offsetRight.y)
                bgPath.lineTo(offsetRight.x, size.height)
                bgPath.lineTo(offsetCenter.x, size.height)
                bgPath.lineTo(offsetCenter.x, offsetCenter.y)
                bgPath.close()

                drawPath(
                    path = bgPath,
                    style = Fill,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            BgPathColor,
                            BgPathColor
                        )
                    )
                )

                drawLine(
                    color = Color.LightGray,
                    start = offsetCenter,
                    end = Offset(offsetCenter.x, size.height),
                    strokeWidth = 2.dp.toPx(),
                    pathEffect = PathEffect
                        .dashPathEffect(
                            floatArrayOf(
                                4.dp.toPx(),
                                2.dp.toPx()
                            ), 0f
                        )
                )


                // 绘制线
                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawLine(
                        offsetCenter.x, offsetCenter.y,
                        offsetRight.x, offsetRight.y,
                        blurLinePaint
                    )
                }


            }



            // 中心点
            drawCircle(
                color = Color.DarkGray,
                radius = 3.dp.toPx(),
                center = offsetCenter
            )

        }
    )

}


@Composable
fun DailyWeatherMinTempChartItem(
    maxMinTemp: Int,
    minMinTemp: Int,
    currentMinTemp: Int,
    nextMinTemp: Int?,
    lastMinTep: Int?
) {

    val blurLinePaint = Paint().asFrameworkPaint().apply {
        strokeWidth = 6f
        color = ContextCompat.getColor(LocalContext.current, R.color.textColorPrimaryHintLight)
        maskFilter = BlurMaskFilter(4f, BlurMaskFilter.Blur.SOLID)
    }

    Canvas(
        modifier = Modifier
            .width(70.dp)
            .height(50.dp),
        onDraw = {

            blurLinePaint.maskFilter = BlurMaskFilter(
                4.dp.toPx(),
                BlurMaskFilter.Blur.SOLID
            )

            val paddingTop = 20.dp.toPx()

            val paddingBottom = 12.dp.toPx()

            val offsetCenter = Offset(
                size.width / 2f,
//                    size.height/2f
                (size.height - paddingBottom) -
                        (size.height - paddingTop - paddingBottom) * ((currentMinTemp - minMinTemp) * 1f / maxMinTemp)
            )

            lastMinTep?.let {
                val lastCenterHeight =
                    (size.height - paddingBottom) -
                            (size.height - paddingTop - paddingBottom) *
                            ((it - minMinTemp) * 1f / maxMinTemp)
                val offsetLeft = Offset(
                    0f,
                    lastCenterHeight / 2 +
                            offsetCenter.y / 2
                )

                val bgPath = Path()
                bgPath.moveTo(offsetLeft.x, offsetLeft.y)
                bgPath.lineTo(offsetLeft.x, 0F)
                bgPath.lineTo(offsetCenter.x, 0f)
                bgPath.lineTo(offsetCenter.x, offsetCenter.y)
                bgPath.close()

                // 绘制背景
                drawPath(
                    path = bgPath,
                    style = Fill,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            BgPathColor,
                            BgPathColor
                        )
                    )
                )

                // 绘制线
                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawLine(
                        offsetCenter.x, offsetCenter.y,
                        offsetLeft.x, offsetLeft.y,
                        blurLinePaint
                    )
                }





            }



            nextMinTemp?.let {
                val nextCenterHeight =
                    (size.height - paddingBottom) -
                            (size.height - paddingTop - paddingBottom) *
                            ((it - minMinTemp) * 1f / maxMinTemp)
                val offsetRight = Offset(
                    size.width,
                    nextCenterHeight / 2 +
                            offsetCenter.y / 2
                )

                val bgPath = Path()
                bgPath.moveTo(offsetRight.x, offsetRight.y)
                bgPath.lineTo(offsetRight.x, 0f)
                bgPath.lineTo(offsetCenter.x, 0f)
                bgPath.lineTo(offsetCenter.x, offsetCenter.y)
                bgPath.close()

                drawPath(
                    path = bgPath,
                    style = Fill,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            BgPathColor,
                            BgPathColor
                        )
                    )
                )

                // 绘制线
                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawLine(
                        offsetCenter.x, offsetCenter.y,
                        offsetRight.x, offsetRight.y,
                        blurLinePaint
                    )
                }
            }


            drawLine(
                color = Color.LightGray,
                start = offsetCenter,
                end = Offset(offsetCenter.x, 0f),
                strokeWidth = 2.dp.toPx(),
                pathEffect = PathEffect
                    .dashPathEffect(
                        floatArrayOf(
                            4.dp.toPx(),
                            2.dp.toPx()
                        ), 0f
                    )
            )




            // 中心点
            drawCircle(
                color = Color.DarkGray,
                radius = 3.dp.toPx(),
                center = offsetCenter
            )

        }
    )

}
