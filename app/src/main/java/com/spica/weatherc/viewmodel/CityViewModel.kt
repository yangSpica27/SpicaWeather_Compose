package com.spica.weatherc.viewmodel

import androidx.lifecycle.ViewModel
import com.spica.weatherc.model.city.CityBean
import com.spica.weatherc.persistence.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CityViewModel @Inject  constructor(
    private val cityRepository: CityRepository,
    private val cities:List<CityBean>
):ViewModel() {


    fun getAllCities() = cities


}