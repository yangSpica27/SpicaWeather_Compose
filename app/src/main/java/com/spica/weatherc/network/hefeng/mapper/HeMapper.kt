package com.spica.weatherc.network.hefeng.mapper

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiSuccessModelMapper
import com.spica.weatherc.model.weather.AirBean
import com.spica.weatherc.model.weather.toAir
import me.spica.weather.model.weather.DailyWeatherBean
import me.spica.weather.model.weather.HourlyWeatherBean
import com.spica.weatherc.model.weather.LifeIndexBean
import me.spica.weather.model.weather.NowWeatherBean
import me.spica.weather.model.weather.toDailyWeatherBean
import me.spica.weather.model.weather.toHourlyWeatherBean
import com.spica.weatherc.model.weather.toLifeIndexBean
import me.spica.weather.model.weather.toNowWeatherBean
import com.spica.weatherc.network.hefeng.HeCode
import com.spica.weatherc.network.hefeng.air.Air
import com.spica.weatherc.network.hefeng.daily.DailyWeather
import com.spica.weatherc.network.hefeng.hourly.HourlyWeather
import com.spica.weatherc.network.hefeng.index.LifeIndex
import com.spica.weatherc.network.hefeng.now.NowWeather

object SuccessDailyWeatherMapper : ApiSuccessModelMapper<DailyWeather, List<DailyWeatherBean>> {

    @Throws(RuntimeException::class)
    override fun map(apiErrorResponse: ApiResponse.Success<DailyWeather>):
        List<DailyWeatherBean> {

        if (apiErrorResponse.data.code == HeCode.Ok.code) {
            return apiErrorResponse.data.daily.map {
                it.toDailyWeatherBean()
            }
        } else {
            throw RuntimeException(apiErrorResponse.data.code)
        }
    }
}

object SuccessNowWeatherMapper : ApiSuccessModelMapper<NowWeather, NowWeatherBean> {
    @Throws(RuntimeException::class)
    override fun map(apiErrorResponse: ApiResponse.Success<NowWeather>):
        NowWeatherBean {

        if (apiErrorResponse.data.code == HeCode.Ok.code) {
            return apiErrorResponse.data.now.toNowWeatherBean()
        } else {
            throw RuntimeException(apiErrorResponse.data.code)
        }
    }
}

object SuccessHourlyWeatherMapper : ApiSuccessModelMapper<HourlyWeather, List<HourlyWeatherBean>> {

    @Throws(RuntimeException::class)
    override fun map(apiErrorResponse: ApiResponse.Success<HourlyWeather>):
        List<HourlyWeatherBean> {

        if (apiErrorResponse.data.code == HeCode.Ok.code) {
            return apiErrorResponse.data.hourly.map {
                it.toHourlyWeatherBean()
            }
        } else {
            throw RuntimeException(apiErrorResponse.data.code)
        }
    }
}

object SuccessLifeIndexWeatherMapper : ApiSuccessModelMapper<LifeIndex, List<LifeIndexBean>> {

    @Throws(RuntimeException::class)
    override fun map(apiErrorResponse: ApiResponse.Success<LifeIndex>):
        List<LifeIndexBean> {

        if (apiErrorResponse.data.code == HeCode.Ok.code) {
            return apiErrorResponse.data.daily.map {
                it.toLifeIndexBean()
            }
        } else {
            throw RuntimeException(apiErrorResponse.data.code)
        }
    }
}


object SuccessAirMapper : ApiSuccessModelMapper<Air, AirBean> {

    @Throws(RuntimeException::class)
    override fun map(apiErrorResponse: ApiResponse.Success<Air>): AirBean {
        if (apiErrorResponse.data.code == HeCode.Ok.code) {
            return apiErrorResponse.data.now.toAir()
        } else {
            throw java.lang.RuntimeException(apiErrorResponse.data.code)
        }
    }

}