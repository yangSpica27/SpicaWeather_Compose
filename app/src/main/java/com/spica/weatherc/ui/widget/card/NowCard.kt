package com.spica.weatherc.ui.widget.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.spica.weather.model.weather.Weather


@Composable
fun NowCard(weather: Weather?) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(4.dp)
    ) {

        Text(text = "NowWeather")

    }
}