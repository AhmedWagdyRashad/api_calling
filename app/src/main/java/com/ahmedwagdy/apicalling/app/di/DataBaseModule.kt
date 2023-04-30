package com.ahmedwagdy.apicalling.app.di

import android.app.Application
import androidx.room.Room
import com.ahmedwagdy.apicalling.data.local.MyDao
import com.ahmedwagdy.apicalling.data.local.MyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): MyDataBase =
        Room.databaseBuilder(app, MyDataBase::class.java, "my_data_base")
            .fallbackToDestructiveMigration()
            .build()
    @Provides
    @Singleton
    fun provideMyDao(myDataBase: MyDataBase):MyDao{
        return myDataBase.myDao()
    }
}