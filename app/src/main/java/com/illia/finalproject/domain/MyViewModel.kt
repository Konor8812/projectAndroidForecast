package com.illia.finalproject.domain

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.illia.finalproject.data.model.WeatherForecastDTO
import com.illia.finalproject.data.retrofit.OpenWeatherMapApi
import com.illia.finalproject.data.retrofit.MyRetrofitClient
import com.illia.finalproject.data.database.WeatherForecastDatabase
import com.illia.finalproject.data.model.WeatherForecastResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.Instant

class MyViewModel : ViewModel() {

    private val latitudeAndLongitudePattern = "((-)?\\d+(\\.\\d+)?)".toRegex()

    val retrofit = MyRetrofitClient.getInstance()
    val openWeatherMapApi = retrofit.create(OpenWeatherMapApi::class.java)
    val database = WeatherForecastDatabase.getInstance()

    suspend fun requestDataFromDb(
        latitude: String,
        longitude: String,
        nod: String
    ): List<WeatherForecastDTO> {

        if (validateCoordinates(latitude, longitude, nod)
        ) {
            try {
                val numOfDaysToSearch = nod.toInt()
                val lat = latitude.toDouble()
                val lon = longitude.toDouble()

                val currentDate = Instant.now().epochSecond

                val result = mutableListOf<WeatherForecastDTO>()
                GlobalScope.launch {
                    val gson = Gson()
                    val dbResponse = database.forecastDAO()
                        .getForecastFromNow(currentDate, lat, lon, numOfDaysToSearch * 24 * 60 * 60)

                    dbResponse.forEach { wf ->
                        println("value from db added to list")

                        val weatherForecastDto = WeatherForecastDTO(
                            wf.overallState,
                            wf.dateAsString,
                            wf.image,
                            wf.location,
                            gson.fromJson(wf.serializedForecast, WeatherForecastResponse.Weather::class.java).toString()
                        )

                        result.add(weatherForecastDto)
                    }
                    println("list size = " + result.size)
                    if (result.isEmpty()) {
                        result.addAll(requestDataFromInternet(latitude, longitude, nod, true))
                    }
                }.join()
                return result
            } catch (ex: Exception) {
                //ignored
            }
        }
        return listOf()
    }

    suspend fun requestDataFromInternet(
        latitude: String,
        longitude: String,
        nod: String,
        areCoordinatesValidated : Boolean
    ): List<WeatherForecastDTO> {
        var result : List<WeatherForecastDTO> = listOf()

        println("coordinates to validate $latitude $longitude")
        if (areCoordinatesValidated || validateCoordinates(latitude, longitude, nod)){
            GlobalScope.launch {
                val lat = latitude.toDouble()
                val lon = longitude.toDouble()
                val numOfDaysToSearch = nod.toInt()

                val response = openWeatherMapApi.getForecast(lat, lon)
                if (response.isSuccessful) {
                    val resp = response.body();
                    result = resp?.parseResponse(numOfDaysToSearch)!!
                }
            }.join()
        }
        return  result
    }

    private fun validateCoordinates(latitude: String, longitude: String, nod:String) : Boolean{
        if (latitudeAndLongitudePattern.matches(latitude) && latitudeAndLongitudePattern.matches(longitude)){
            try{
                nod.toInt()
            }catch (ex: Exception){
                return false
            }
            val lat = latitude.toDouble()
            val lon = longitude.toDouble()
            if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
                return false
            }
            return true
        }
        return false
    }
}




