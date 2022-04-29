package com.spica.weatherc.ui.widget.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * 空气质量信息卡片
 */
@Composable
fun AirCard() {
    Card {

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

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                
                
                Row() {
                    
                }

            }

        }

    }
}