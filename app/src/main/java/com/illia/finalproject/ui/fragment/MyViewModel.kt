package com.illia.finalproject.ui.fragment

import androidx.lifecycle.ViewModel
import com.illia.finalproject.model.WeatherForecastDTO
import com.illia.finalproject.data.retrofit.OpenWeatherMapApi
import com.illia.finalproject.data.retrofit.MyRetrofitClient
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MyViewModel : ViewModel() {

    private val latitudeAndLongitudePattern = "((-)?\\d+(\\.\\d+)?) ((-)?\\d+(\\.\\d+)?)".toRegex()


    val retrofit = MyRetrofitClient.getInstance()
    val openWeatherMapApi = retrofit.create(OpenWeatherMapApi::class.java)

    suspend fun requestDataFromDb(
        latitude: String,
        longitude: String,
        nod: String
    ): List<WeatherForecastDTO> {

        if (latitudeAndLongitudePattern.matches(latitude) && latitudeAndLongitudePattern.matches(longitude)) {

            try {
                var numOfDaysToSearch = nod.toInt()
                val lat = latitude.toDouble()
                val lon = longitude.toDouble()

                var result: List<WeatherForecastDTO>
                var currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

                //database ref

//                if (result.isEmpty()) {
//                    result = requestDataFromInternet(lat, lon)
//                }
//                return result
            } catch (ex: Exception) {
                //ignored
            }
        }
        return listOf()

    }

    suspend fun requestDataFromInternet(
        latitude: Double,
        longitude: Double
    ): List<WeatherForecastDTO> {
        println("requested values")

        val result: List<WeatherForecastDTO>


        val response = openWeatherMapApi.getForecast()
        if (response.isSuccessful) {
            println(response.headers())
            val resp = response.body();
            result = resp?.parseIntoDTO() ?: listOf()

        } else {
            result = listOf()
        }
        return result
    }


}




