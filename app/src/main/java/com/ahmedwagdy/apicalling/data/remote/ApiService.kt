package com.ahmedwagdy.apicalling.data.remote

import com.ahmedwagdy.apicalling.BuildConfig
import com.ahmedwagdy.apicalling.domain.entity.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val APP_ID = BuildConfig.API_KEY
    }

    @GET("forecast")
    suspend fun getCityWeather(
        @Query("q") city: String,
        @Query("appid") appID: String = APP_ID
    ): Response<WeatherResponse>
}