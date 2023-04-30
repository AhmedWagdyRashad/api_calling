package com.ahmedwagdy.apicalling.domain.entity

data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherNode>,
    val message: Int
)