package com.ahmedwagdy.apicalling.domain.usecases

import com.ahmedwagdy.apicalling.domain.repo.WeatherRepo

class GetWeatherData(private val myRepo: WeatherRepo) {
    suspend operator fun invoke (city:String) = myRepo.getWeatherDataFromRemote(city)
}