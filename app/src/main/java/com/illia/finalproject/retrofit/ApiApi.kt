package com.illia.finalproject.retrofit

import com.illia.finalproject.model.WeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiApi {
    @GET("value")
        suspend fun getSmth() : Response<Something>

    @GET("forecast?lat=50.523&lon=30.24&appid=23b08b8bb2b1baec815f8e52eb970516&mode=json")
    suspend fun getForecast() : Response<WeatherForecastResponse>
}