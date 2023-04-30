package com.ahmedwagdy.apicalling.data.repo

import com.ahmedwagdy.apicalling.data.local.MyDao
import com.ahmedwagdy.apicalling.data.remote.ApiService
import com.ahmedwagdy.apicalling.domain.entity.WeatherData
import com.ahmedwagdy.apicalling.domain.entity.WeatherResponse
import com.ahmedwagdy.apicalling.domain.mapper.WeatherResponseMapper
import com.ahmedwagdy.apicalling.domain.repo.WeatherRepo
import com.ahmedwagdy.apicalling.domain.util.Result

class WeatherRepoImp(
    private val apiService: ApiService,
    private val myDao: MyDao
): WeatherRepo {
    override suspend fun getWeatherDataFromRemote(city: String): Result<List<WeatherData>> {
        return try {
            val response = apiService.getCityWeather(city)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Result.Success(WeatherResponseMapper.toWeatherDataList(result))
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "An error occurred")
        }
    }

}