package com.spica.weatherc.persistence.repository

import androidx.annotation.WorkerThread
import com.spica.weatherc.model.city.CityBean
import com.spica.weatherc.persistence.dao.CityDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Suppress("unused")
class CityRepository @Inject constructor(
    private val cityDao: CityDao
) {

    fun allCityFlow() = cityDao.getAllDistinctUntilChanged()

    @WorkerThread
    suspend fun addCity(cityBean: CityBean): String {
        var result = "添加成功"
        cityDao.getAllList().forEach {
            if (it.cityName == cityBean.cityName) {
                result = "已经添加过了"
                return result
            }
        }
        cityDao.insert(cityBean)
        return result
    }

    @WorkerThread
    suspend fun selected(cityBean: CityBean) = withContext(Dispatchers.IO) {
        cityDao.getAllList().forEach {
            it.isSelected = false
            cityDao.update(it)
        }
        cityBean.isSelected = true
        cityDao.insert(cityBean)
    }

    @WorkerThread
    fun deleteCity(cityBean: CityBean) {
        cityDao.deleteCity(cityBean = cityBean)
    }

    fun selectedCityFlow() = cityDao.getSelectCity()
}
