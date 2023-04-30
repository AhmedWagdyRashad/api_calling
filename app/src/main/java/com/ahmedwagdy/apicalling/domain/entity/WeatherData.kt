package com.ahmedwagdy.apicalling.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("weather_data")
data class WeatherData(
    @PrimaryKey
    val dt_txt: String,
    val temp: Double,
    val humidity: Int,
    val description: String
)
