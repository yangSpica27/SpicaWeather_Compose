package com.spica.weatherc.network.hefeng

import com.skydoves.sandwich.ApiResponse
import com.spica.weatherc.network.hefeng.air.Air
import com.spica.weatherc.network.hefeng.daily.DailyWeather
import com.spica.weatherc.network.hefeng.hourly.HourlyWeather
import com.spica.weatherc.network.hefeng.index.LifeIndex
import com.spica.weatherc.network.hefeng.now.NowWeather
import javax.inject.Inject

@Suppress("unused")
class HeClient @Inject constructor(private val heService: HeService) {

    suspend fun getNowWeather(
        lon: String,
        lat: String
    ): ApiResponse<NowWeather> = heService.getNowWeather(
        location = "$lon,$lat"
    )

    suspend fun get7DWeather(
        lon: String,
        lat: String
    ): ApiResponse<DailyWeather> = heService.get7DayWeather(
        location = "$lon,$lat"
    )

    suspend fun get24HWeather(
        lon: String,
        lat: String
    ): ApiResponse<HourlyWeather> = heService.get24HWeather(
        location = "$lon,$lat"
    )

    suspend fun getLifeIndex(
        lon: String,
        lat: String
    ): ApiResponse<LifeIndex> = heService.get1dIndex(
        location = "$lon,$lat"
    )

    suspend fun getAirNow(
        lon: String,
        lat: String
    ): ApiResponse<Air> = heService.getNowAir(
        location = "$lon,$lat"
    )
}
