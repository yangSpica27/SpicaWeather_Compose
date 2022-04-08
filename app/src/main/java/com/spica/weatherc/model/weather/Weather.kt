package com.spica.weatherc.model.weather

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import me.spica.weather.model.weather.DailyWeatherBean
import me.spica.weather.model.weather.HourlyWeatherBean
import me.spica.weather.model.weather.NowWeatherBean
import me.spica.weather.model.weather.WeatherBeanConverter


@Entity
@TypeConverters(WeatherBeanConverter::class)
data class Weather(
    val todayWeather: NowWeatherBean,
    val dailyWeather: List<DailyWeatherBean>,
    val hourlyWeather: List<HourlyWeatherBean>,
    val lifeIndexes: List<LifeIndexBean>,
    val air: AirBean,
    @PrimaryKey(autoGenerate = false)
    val id: Long = 1L
)