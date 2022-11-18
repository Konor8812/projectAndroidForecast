package com.illia.finalproject.data.retrofit

import com.illia.finalproject.model.WeatherForecastResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {
    @GET("forecast?appid=23b08b8bb2b1baec815f8e52eb970516&mode=json")
    suspend fun getForecast(@Query(value = "lat") lat:Double,@Query(value = "lon")lon:Double) : Response<WeatherForecastResponse>
}
