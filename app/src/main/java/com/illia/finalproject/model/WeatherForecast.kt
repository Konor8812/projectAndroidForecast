package com.illia.finalproject.model

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WeatherForecast ( private var okStatus: Boolean,
                        private var errorMessage: String? = null ) {

    private var location: Location? = null
    private var sunRiseTime: String? = null
    private var sunSetTime: String? = null
    private var weatherList: MutableList<Weather> = ArrayList()


    private val DATE_FORMAT = "dd.MM.yyyy HH:mm"
    private val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    private val DECIMAL_FORMAT: DecimalFormat = DecimalFormat("#.##")

    fun addWeatherForecast(weather: Weather) {
        weatherList.add(weather)
    }

    override fun toString(): String {
        return "WeatherForecast{" +
                "location=" + location +
                ", sunRiseTime='" + sunRiseTime + '\'' +
                ", sunSetTime='" + sunSetTime + '\'' +
                ", weatherList=" + weatherList +
                '}'
    }

    fun getForecastForTimePeriod(days: Int): String? {
        if (!okStatus) {
            return errorMessage
        }

        var days = days
        if (days > 5) {
            days = 5
        } else if (days <= 0) {
            days = 1
        }

        val sb = StringBuilder()
        sb.append("Weather forecast for ").append(location.toString()).append(" on ")
            .append(LocalDate.now().format(DATE_TIME_FORMATTER)).append("\n")
            .append("Sun rises at ").append(sunRiseTime).append("\nSets down at ")
            .append(sunSetTime).append("\n\n")
        for (i in 0 until days * 8) { // 24 hours 3 interval -> 24 / 3 = 8
            val weather: Weather = weatherList[i]
            sb.append(weather.getTime())
                .append("\nOverall state: ").append(weather.getGeneralState())
                .append("\nTemperature: ").append(weather.getTemperature())
                .append("°C, feels like ").append(weather.getTemperatureFeelsLike())
                .append("°C\nPrecipitation probability: ")
                .append(weather.getPrecipitationProbability())
                .append("\nHumidity: ").append(weather.getHumidity())
                .append("%\nWind speed: ").append(weather.getWindSpeed()).append(" mps (")
                .append(DECIMAL_FORMAT.format(weather.getWindSpeed().toDouble() * 3.6))
                .append(" km/h)\n\n")
        }
        return sb.toString().replace("\n".toRegex(), System.lineSeparator())
    }

    fun isOkStatus(): Boolean {
        return okStatus
    }

    fun getErrorMessage(): String? {
        return errorMessage
    }

    fun getLocation(): Location? {
        return location
    }

    fun getSunRiseTime(): String? {
        return sunRiseTime
    }

    fun getSunSetTime(): String? {
        return sunSetTime
    }

    fun getWeatherList(): List<Weather?> {
        return weatherList
    }

    fun setOkStatus(okStatus: Boolean) {
        this.okStatus = okStatus
    }


    fun setLocation(location: Location) {
        this.location = location
    }

    fun setSunRiseTime(sunRiseTime: String) {
        this.sunRiseTime = sunRiseTime
    }

    fun setSunSetTime(sunSetTime: String) {
        this.sunSetTime = sunSetTime
    }

    fun setWeatherList(weatherList: List<Weather>?) {
        this.weatherList = weatherList as MutableList<Weather>
    }
}