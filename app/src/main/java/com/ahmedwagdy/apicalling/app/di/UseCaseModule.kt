package com.example.fawrytask.di

import com.ahmedwagdy.apicalling.domain.repo.WeatherRepo
import com.ahmedwagdy.apicalling.domain.usecases.GetWeatherData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetWeatherUseCase(weatherRepo: WeatherRepo): GetWeatherData{
        return GetWeatherData(weatherRepo)
    }
}