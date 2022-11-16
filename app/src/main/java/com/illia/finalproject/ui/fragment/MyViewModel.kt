package com.illia.finalproject.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.illia.finalproject.model.WeatherForecastDTO
import com.illia.finalproject.retrofit.ApiApi
import com.illia.finalproject.retrofit.MyRetrofitClient
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {


    fun requestData(
        latitude: Double,
        longitude: Double
    ) : List<WeatherForecastDTO>{

        val retrofit = MyRetrofitClient.getInstance()
        val apiApi = retrofit.create(ApiApi::class.java)
        val result = mutableListOf<WeatherForecastDTO>()
        viewModelScope.launch {
            val response = apiApi.getForecast()
            if (response.isSuccessful) {
                println(response.headers())
                var resp = response.body();
                println("successfully loaded data")
                resp?.parseIntoDTO()
            } else {
                requestDataFromDb(latitude, longitude)
            }
        }

        return result
    }

    fun requestDataFromDb(latitude: Double, longitude: Double) {

    }

    fun requestDataFromInternet() {

    }
}



