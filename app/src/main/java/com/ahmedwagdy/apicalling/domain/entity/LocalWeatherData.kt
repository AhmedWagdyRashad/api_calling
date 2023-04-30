package com.ahmedwagdy.apicalling.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "weather_data")
data class LocalWeatherData(
  //  @PrimaryKey(false)
    val id: Int,
   val weatherDataList: List<WeatherData>
)

// add type converter to store list in database
