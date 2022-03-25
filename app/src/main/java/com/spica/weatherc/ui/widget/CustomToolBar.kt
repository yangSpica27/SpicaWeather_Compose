package com.spica.weatherc.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomToolbar(
    leftWidget: @Composable () -> Unit,
    rightWidget: @Composable () -> Unit,
    title: @Composable () -> Unit,
    iconSize: Dp = 36.dp
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .width(iconSize)
                .height(iconSize)
        ) {
            leftWidget()
        }
        Box(Modifier.weight(1f)) {
            title()
        }
        Box(
            Modifier
                .width(iconSize)
                .height(iconSize)
        ) {
            rightWidget()
        }
    }
}



