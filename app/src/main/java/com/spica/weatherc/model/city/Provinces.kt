package com.spica.weatherc.model.city

import com.spica.weatherc.model.city.Province
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Provinces(
    val provinces: List<Province>
)
