package com.spica.weatherc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spica.weatherc.model.city.CityBean
import com.spica.weatherc.model.weather.AirBean
import com.spica.weatherc.model.weather.LifeIndexBean
import com.spica.weatherc.persistence.dao.WeatherDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.spica.weather.model.weather.DailyWeatherBean
import me.spica.weather.model.weather.HourlyWeatherBean
import me.spica.weather.model.weather.NowWeatherBean
import com.spica.weatherc.model.weather.Weather
import me.spica.weather.repository.HeRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel  @Inject constructor(
    private val repository: HeRepository,
    private val weatherDao: WeatherDao
) :ViewModel(){

    private val cityFlow: MutableStateFlow<CityBean?> = MutableStateFlow(null)

    // 设置城市


    fun changeCity(cityBean: CityBean) {
        Timber.d("请求进入")
        viewModelScope.launch {
            cityFlow.value = CityBean(
                cityName = cityBean.cityName,
                sortName = cityBean.sortName,
                lon = cityBean.lon,
                lat = cityBean.lat,
                isSelected = cityBean.isSelected
            )
        }
    }

    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)

     val _isLoading = MutableStateFlow(false)

    // 即时天气
    @OptIn(ExperimentalCoroutinesApi::class)
    private val nowWeatherFlow: Flow<NowWeatherBean?> =
        cityFlow
            .filterNotNull()
            .flatMapLatest {
                repository.fetchNowWeather(
                    lon = it.lon,
                    lat = it.lat,
                    onError = { message ->
                        _errorMessage.value = message
                    }
                )
            }


    @OptIn(ExperimentalCoroutinesApi::class)
    private val dailyWeatherFlow: Flow<List<DailyWeatherBean>?> =
        cityFlow.filterNotNull().flatMapLatest {
            repository.fetchDailyWeather(
                lon = it.lon,
                lat = it.lat,
                onError = { message ->
                    _errorMessage.value = message
                }
            )
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val hourlyWeatherFlow: Flow<List<HourlyWeatherBean>?> =
        cityFlow
            .filterNotNull()
            .flatMapLatest {
                repository.fetchHourlyWeather(
                    lon = it.lon,
                    lat = it.lat,
                    onError = { message ->
                        _errorMessage.value = message
                    }
                )
            }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val currentIndices: Flow<List<LifeIndexBean>?> =
        cityFlow
            .filterNotNull()
            .flatMapLatest {
                repository.fetchTodayLifeIndex(
                    lon = it.lon,
                    lat = it.lat,
                    onError = { message ->
                        _errorMessage.value = message
                    }
                )
            }

    private val nowAir: Flow<AirBean?> =
        cityFlow
            .filterNotNull()
            .flatMapLatest {
                repository.fetchNowAir(
                    lon = it.lon,
                    lat = it.lat,
                    onError = { message ->
                        _errorMessage.value = message
                    }
                )
            }

    @OptIn(ExperimentalCoroutinesApi::class)
    val weatherCacheFlow = cityFlow.filterNotNull().flatMapLatest {
        weatherDao.getWeatherFlowDistinctUntilChanged()
    }

    val weatherFlow = combine(
        nowWeatherFlow,
        dailyWeatherFlow,
        hourlyWeatherFlow,
        currentIndices,
        nowAir,
    ) { nowWeather, dailyWeather, hourWeather, lifeIndexes, nowAir ->
        if (nowWeather != null
            && dailyWeather != null
            && hourWeather != null &&
            lifeIndexes != null &&
            nowAir != null
        ) {
            Timber.e("触发请求")
            val result = Weather(
                nowWeather,
                dailyWeather,
                hourWeather,
                lifeIndexes,
                nowAir,
                1L
            )
            viewModelScope.launch(Dispatchers.IO) {
                weatherDao.insertWeather(result)
            }
            return@combine result
        }

        if (nowWeather == null) {
            Timber.e("now==nu;;")
        }
        if (dailyWeather == null) {
            Timber.e("daily==null")
        }
        if (hourWeather == null) {
            Timber.e("hourWeather==null")
        }
        if (lifeIndexes == null) {
            Timber.e("lifeIndex==null")
        }
        if (nowAir == null) {
            Timber.e("nowAir===null")
        }
        return@combine null
    }.onStart {
        _isLoading.value = true
    }.onCompletion {
        _isLoading.value = false
    }



}