package com.ahmedwagdy.apicalling.app.ui.weatherview.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedwagdy.apicalling.domain.entity.WeatherData
import com.ahmedwagdy.apicalling.domain.entity.WeatherResponse
import com.ahmedwagdy.apicalling.domain.util.Result
import com.ahmedwagdy.apicalling.domain.usecases.GetWeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase:GetWeatherData
)  : ViewModel() {
    private val _weatherData: MutableStateFlow<List<WeatherData>?> = MutableStateFlow(null)
    val weatherData: StateFlow<List<WeatherData>?> = _weatherData
    private val _error: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error
    fun getWeatherData(city: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                when(val response = getWeatherUseCase(city)){
                    is Result.Success -> {
                        _weatherData.value = response.data
                        _error.value = false
                        Log.e("WeatherViewModel" , "${response.data}")
                    }
                    is Result.Error -> {
                        _error.value = true
                        Log.e("WeatherViewModel" , "${response.message}")
                    }
                }
            } catch (e: Exception){
                Log.e("CategoryViewModel", e.message.toString())
            }
        }
    }
}