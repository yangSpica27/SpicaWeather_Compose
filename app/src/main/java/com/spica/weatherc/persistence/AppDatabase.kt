package com.spica.weatherc.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spica.weatherc.model.city.CityBean
import com.spica.weatherc.persistence.dao.CityDao
import com.spica.weatherc.persistence.dao.WeatherDao
import com.spica.weatherc.model.weather.Weather


@Database(entities = [CityBean::class, Weather::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun weatherDao(): WeatherDao
}
