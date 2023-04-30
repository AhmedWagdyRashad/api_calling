package com.ahmedwagdy.apicalling.domain.repo

import com.ahmedwagdy.apicalling.domain.entity.WeatherData
import com.ahmedwagdy.apicalling.domain.entity.WeatherResponse
import com.ahmedwagdy.apicalling.domain.util.Result

interface WeatherRepo {
    suspend fun getWeatherDataFromRemote(city:String): Result<List<WeatherData>>
}