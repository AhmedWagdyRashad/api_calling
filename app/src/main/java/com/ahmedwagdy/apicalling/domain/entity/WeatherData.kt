package com.ahmedwagdy.apicalling.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data")
data class WeatherData(
    @PrimaryKey(false)
    val dt_txt: String,
    val temp: Double,
    val humidity: Int,
    val description: String
)
