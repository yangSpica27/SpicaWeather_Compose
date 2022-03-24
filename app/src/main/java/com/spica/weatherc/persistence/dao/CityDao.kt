@file:Suppress("unused")

package com.spica.weatherc.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.spica.weatherc.model.city.CityBean
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged


@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg cityBean: CityBean)

    @Query("SELECT * FROM t_city WHERE isSelected =:isSelect LIMIT 0,1")
    fun getSelectCity(isSelect: Boolean = true): Flow<CityBean?>

    @Query("SELECT * FROM t_city")
    fun getCities(): Flow<List<CityBean>>

    @Query("SELECT * FROM t_city")
    fun getAllList(): List<CityBean>

    @ExperimentalCoroutinesApi
    fun getAllDistinctUntilChanged() = getCities().distinctUntilChanged()

    @Update
    fun update(cityBean: CityBean)

    @Delete
    fun deleteCity(cityBean: CityBean)
}
