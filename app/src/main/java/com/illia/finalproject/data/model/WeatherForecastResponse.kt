package com.illia.finalproject.data.model

import android.database.sqlite.SQLiteConstraintException
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.illia.finalproject.data.database.WeatherForecastDatabase
import java.text.DecimalFormat
import java.time.Instant
import kotlin.math.roundToInt

class WeatherForecastResponse(
    @SerializedName("city")
    var city: City?,
    @SerializedName("list")
    var weatherList: List<Weather>
) {

    inner class Weather(
        @SerializedName("dt_txt")
        var dateTime: String,
        @SerializedName("dt")
        var dateTimeAsLong: Long,
        @SerializedName("main")
        var mainDescription: MainDescription,
        @SerializedName("weather")
        var overallState: List<OverallState>,
        @SerializedName("wind")
        var wind: Wind
    ) {

        inner class MainDescription(
            @SerializedName("temp")
            var temperature: Double,
            @SerializedName("humidity")
            var humidity: Double
        ) {
            override fun toString(): String {
                return "temperature ~ ${DecimalFormat("#.#").format(temperature - 273.15)}, humidity = ${humidity.roundToInt()}%"
            }
        }

        inner class Wind(
            @SerializedName("speed")
            var speed: Double
        ) {
            override fun toString(): String {
                return "$speed m/s (${DecimalFormat("#.##").format(speed * 3.6)} km/h)"
            }
        }

        inner class OverallState(
            @SerializedName("description")
            var state: String
        ) {
            override fun toString(): String {
                return "Mostly $state"
            }

        }

        override fun toString(): String {
            return "${overallState.get(0)}, $mainDescription, \nWind $wind"
        }
    }

     fun getForecastForDays(nod: Int): String {
        var sb: StringBuilder = StringBuilder()

        for (i in 0..nod * 8) {

            val record = weatherList.get(i)
            sb.append(record.dateTime).append("\n")
                .append(record).append("\n")
        }
        sb.append("\n")
        return "Forecast for $city $sb"
    }


     fun parseResponse(nod :Int): List<WeatherForecastDTO> {
        val values = mutableListOf<WeatherForecastDTO>()
        val gson = Gson()
        val weatherForecastDatabase = WeatherForecastDatabase.getInstance()
        val dao = weatherForecastDatabase.forecastDAO()
        var itemsCount = 0

        val lat = city!!.coordinates!!.latitude!!
        val lon = city!!.coordinates!!.longitude!!
        val cityName = city?.getCityName() ?: "Unknown location"

        weatherList.forEach{ x ->
            val dateAsString = x.dateTime
            val dateAsLong = x.dateTimeAsLong
            val overallState = x.overallState.get(0).state
            val imageUrl = resolveImage(overallState)
            val serializedEntity = gson.toJson(x)

            val weatherForecastEntity = WeatherForecast(dateAsLong, dateAsString,
            cityName, overallState, imageUrl, lat, lon, serializedEntity)
            try {
                dao.insert(weatherForecastEntity)
            }catch (ex:SQLiteConstraintException){
                dao.updateEntities(weatherForecastEntity)
            }
            if (itemsCount < nod * 8){
                values.add(WeatherForecastDTO(overallState, x.dateTime, imageUrl, cityName, x.toString()))
                itemsCount++
        }
        }

        return values

    }

    fun getForecastForNumberOfDays(nod: Int) {
        var sb: StringBuilder = StringBuilder()
        for (i in 0..nod * 8) {
            val record = weatherList.get(i)
            sb.append(record.dateTime).append("\n")
                .append(record).append("\n")
        }
        sb.append("\n")
    }

    private fun resolveImage(state: String): String {
        return when (state.trim()) {
            "broken clouds" -> "https://media.istockphoto.com/photos/cirrocumulus-clouds-cloudscape-picture-id645173476?b=1&k=20&m=645173476&s=170667a&w=0&h=0wdytj1LA3mA1Jzp0j6_rgip60BxH9e5BAAE_vFlJQE="
            "scattered clouds" -> "https://media.istockphoto.com/photos/cirrocumulus-clouds-cloudscape-picture-id645173476?b=1&k=20&m=645173476&s=170667a&w=0&h=0wdytj1LA3mA1Jzp0j6_rgip60BxH9e5BAAE_vFlJQE="
            "few clouds" -> "https://media.istockphoto.com/photos/cirrocumulus-clouds-cloudscape-picture-id645173476?b=1&k=20&m=645173476&s=170667a&w=0&h=0wdytj1LA3mA1Jzp0j6_rgip60BxH9e5BAAE_vFlJQE="
            "overcast clouds" -> "https://images.unsplash.com/photo-1525920980995-f8a382bf42c5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8b3ZlcmNhc3QlMjBza3l8ZW58MHx8MHx8&w=1000&q=80"
            "light rain" -> "https://s7d2.scene7.com/is/image/TWCNews/0622_n13_light_rain"
            "clear sky" -> "https://media.istockphoto.com/id/1004682020/photo/clouds-in-the-blue-sky.jpg?b=1&s=170667a&w=0&k=20&c=tbGtJQgWtrn6W0PVlD3w3uZ_AZkm3qsYkWgWwITgvtk="
            "light snow" -> "https://ukranews.com/upload/news/2017/01/05/586e37587c319-325_1200.jpg"
            "snow" -> "https://illustoon.com/photo/7580.png"
            "moderate rain" -> "https://www.sciline.org/wp-content/uploads/2021/02/cropped-Torrential-Rain-Flooding-and-Climate-Change.jpg"
            else -> "https://static.thenounproject.com/png/119135-200.png"
        }

    }


    inner class City(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("country")
        var country: String?,
        @SerializedName("sunrise")
        var sunRise: Long,
        @SerializedName("sunset")
        var sunSet: Long,
        @SerializedName("coord")
        var coordinates: Coordinates?
    ) {
        override fun toString(): String {
            return "$name ($coordinates), $country:\n Sun rises at " + formatDate(sunRise) + " , sets down at " + formatDate(
                sunSet
            ) + "\n"
        }

        private fun formatDate(seconds: Long): String {
            return "(\\d\\d:\\d\\d:\\d\\d)".toRegex()
                .find(Instant.ofEpochSecond(seconds).toString())?.value
                ?: "Unresolvable"
        }

        fun getCityName() : String {
            if(name == null){
                return "Unknown location"
            }
            return name as String
        }

        inner class Coordinates(
            @SerializedName("lat")
            var latitude: Double?,
            @SerializedName("lon")
            var longitude: Double?
        ) {
            override fun toString(): String {
                return "lat = $latitude, lon = $longitude"
            }
        }
    }

}