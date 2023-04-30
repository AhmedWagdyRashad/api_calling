package com.ahmedwagdy.apicalling.app.di

import com.ahmedwagdy.apicalling.data.local.MyDao
import com.ahmedwagdy.apicalling.data.local.MyDataBase
import com.ahmedwagdy.apicalling.data.remote.ApiService
import com.ahmedwagdy.apicalling.data.repo.WeatherRepoImp
import com.ahmedwagdy.apicalling.domain.repo.WeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideWeatherRepo(apiService: ApiService, myDao: MyDao): WeatherRepo{
        return WeatherRepoImp(apiService, myDao)
    }
}