package com.spica.weatherc.ui.widget.card

import android.graphics.BlurMaskFilter
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
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.spica.weatherc.R
import com.spica.weatherc.ui.theme.BgPathColor
import com.spica.weatherc.ui.theme.LightTextColor
import me.spica.weather.model.weather.DailyWeatherBean


@Composable
fun DailyCard(list: List<DailyWeatherBean>?) {
    val listData = (0..9).toList()
    Card() {
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

    val blurLinePaint = Paint().asFrameworkPaint().apply {
        strokeWidth = 6f
        color = ContextCompat.getColor(LocalContext.current, R.color.textColorPrimaryHintLight)
        maskFilter = BlurMaskFilter(4f, BlurMaskFilter.Blur.SOLID)
    }

    Canvas(
        modifier = Modifier
            .width(70.dp)
            .height(35.dp),
        onDraw = {

            blurLinePaint.maskFilter = BlurMaskFilter(
                4.dp.toPx(),
                BlurMaskFilter.Blur.SOLID
            )

            val paddingTop = 20.dp.toPx()

            val paddingBottom = 20.dp.toPx()

            val offsetCenter = Offset(
                size.width / 2f,
//                    size.height/2f
                (size.height - paddingBottom) -
                        (size.height - paddingTop - paddingBottom) * ((currentValue - minValue) * 1f / maxValue)
            )

            lastMaxTemp?.let {
                val lastCenterHeight =
                    (size.height - paddingBottom) -
                            (size.height - paddingTop - paddingBottom) *
                            ((it - minValue) * 1f / maxValue)
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
                            Color.Transparent
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height)
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


                drawLine(
                    color = Color.LightGray,
                    start = Offset(offsetCenter.x, size.height),
                    end = Offset(offsetLeft.x, size.height),
                    strokeWidth = 2.dp.toPx(),
                    pathEffect = PathEffect
                        .dashPathEffect(
                            floatArrayOf(
                                4.dp.toPx(),
                                2.dp.toPx()
                            ), 0f
                        )
                )


            }



            nextValue?.let {
                val nextCenterHeight =
                    (size.height - paddingBottom) -
                            (size.height - paddingTop - paddingBottom) *
                            ((it - minValue) * 1f / maxValue)
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
                            Color.Transparent
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height)
                    )
                )

//                    drawLine(
//                        color = Color.LightGray,
//                        start = offsetCenter,
//                        end = offsetRight,
//                        strokeWidth = 2.dp.toPx()
//                    )


                // 绘制线
                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawLine(
                        offsetCenter.x, offsetCenter.y,
                        offsetRight.x, offsetRight.y,
                        blurLinePaint
                    )
                }



                drawLine(
                    color = Color.LightGray,
                    start = Offset(offsetCenter.x, size.height),
                    end = Offset(offsetRight.x, size.height),
                    strokeWidth = 2.dp.toPx(),
                    pathEffect = PathEffect
                        .dashPathEffect(
                            floatArrayOf(
                                4.dp.toPx(),
                                2.dp.toPx()
                            ), 0f
                        )
                )

            }

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
    nextMinTemp: Int,
    lastMinTep: Int
) {

    val blurLinePaint = Paint().asFrameworkPaint().apply {
        strokeWidth = 6f
        color = ContextCompat.getColor(LocalContext.current, R.color.textColorPrimaryHintLight)
        maskFilter = BlurMaskFilter(4f, BlurMaskFilter.Blur.SOLID)
    }

    Canvas(
        modifier = Modifier
            .width(70.dp)
            .height(35.dp),
        onDraw = {

        }
    )

}
