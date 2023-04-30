package com.ahmedwagdy.apicalling.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmedwagdy.apicalling.domain.entity.WeatherData

@Database(entities = [WeatherData::class], version = 1, exportSchema = false)
abstract class MyDataBase: RoomDatabase() {
    abstract fun myDao(): MyDao
}