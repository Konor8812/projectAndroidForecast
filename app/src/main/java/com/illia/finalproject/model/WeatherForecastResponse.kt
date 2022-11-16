package com.illia.finalproject.model

import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat
import java.time.Instant
import java.time.LocalTime
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern
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

    public fun getForecastForDays(nod: Int): String {
        var sb: StringBuilder = StringBuilder()

        for (i in 0..nod * 8) {
//            val weatherEntity = // DATABASE REFERENCE ?
            val record = weatherList.get(i)
            sb.append(record.dateTime).append("\n")
                .append(record).append("\n")
        }
        sb.append("\n")
        return "Forecast for $city $sb"
    }

    public fun parseIntoDTO(): List<WeatherForecastDTO> {
        val values = mutableListOf<WeatherForecastDTO>()

        var newDayStartIndex: Int = 0

        while (true) {
            val record = weatherList.get(newDayStartIndex++)
            val overallState = record.overallState.get(0).state
            val dateTime = record.dateTime
            val image = resolveImage(overallState)
            values.add(WeatherForecastDTO(overallState, dateTime, image))
            if (record.dateTime.contains("00:00:00")) {
                break
            }
        }

        while (true){
            // ??
            val record = weatherList.get(newDayStartIndex++)
            val overallState = record.overallState.get(0).state
            val dateTime = record.dateTime
            val image = resolveImage(overallState)
            values.add(WeatherForecastDTO(overallState, dateTime, image))
            if (record.dateTime.contains("00:00:00")) {
                break
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