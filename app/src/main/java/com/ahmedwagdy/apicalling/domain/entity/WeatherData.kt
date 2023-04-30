package com.ahmedwagdy.apicalling.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class WeatherData(
    val dt_txt: String,
    val temp: Double,
    val humidity: Int,
    val description: String
)
