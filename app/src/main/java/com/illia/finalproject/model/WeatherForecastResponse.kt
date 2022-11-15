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
                return "temperature ~ ${DecimalFormat("#.##").format(temperature - 273.15)}, humidity = ${humidity.roundToInt()}%"
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
        ){
            override fun toString(): String {
            return "Mostly $state"
            }

        }

        override fun toString(): String {
            return "${overallState.get(0)}, $mainDescription, \nWind $wind"
        }
    }

    public fun getForecastForDays(nod: Int) :String{
        var  sb : StringBuilder = StringBuilder()

        for (i in 0 .. nod * 8){
//            val weatherEntity = // DATABASE REFERENCE
            val record = weatherList.get(i)
            sb.append(record.dateTime).append("\n")
                .append(record).append("\n")
        }
        sb.append("\n")
        return "Forecast for $city $sb"
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
            return "$name ($coordinates), $country:\n Sun rises at " + formatDate(sunRise) + " , sets down at " + formatDate(sunSet) + "\n"
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