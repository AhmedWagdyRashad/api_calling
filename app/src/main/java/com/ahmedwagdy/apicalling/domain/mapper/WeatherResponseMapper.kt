package com.ahmedwagdy.apicalling.domain.mapper

import com.ahmedwagdy.apicalling.domain.entity.WeatherData
import com.ahmedwagdy.apicalling.domain.entity.WeatherResponse

class WeatherResponseMapper() {

    companion object{
        @JvmStatic
        fun toWeatherDataList(weatherResponse: WeatherResponse):List<WeatherData>{
            return weatherResponse.list.map {
                WeatherData(
                    it.dt_txt,
                    it.main.temp,
                    it.main.humidity,
                    it.weather[0].description
                )
            }
        }
    }

}