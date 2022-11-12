package com.illia.finalproject.model

class Weather (    private var time: String,
                   private var generalState: String,
                   private var precipitationProbability: String,
                   private var windSpeed: String,
                   private var temperature: String,
                   private var temperatureFeelsLike: String,
                   private var pressure: String,
                   private var humidity: String) {

    fun getTime(): String {
        return time
    }

    fun getGeneralState(): String {
        return generalState
    }

    fun getPrecipitationProbability(): String {
        return precipitationProbability
    }

    fun getWindSpeed(): String {
        return windSpeed
    }

    fun getTemperature(): String {
        return temperature
    }

    fun getTemperatureFeelsLike(): String {
        return temperatureFeelsLike
    }

    fun getPressure(): String {
        return pressure
    }

    fun getHumidity(): String {
        return humidity
    }

    fun setTime(time: String) {
        this.time = time
    }

    fun setGeneralState(generalState: String) {
        this.generalState = generalState
    }

    fun setPrecipitationProbability(precipitationProbability: String) {
        this.precipitationProbability = precipitationProbability
    }

    fun setWindSpeed(windSpeed: String) {
        this.windSpeed = windSpeed
    }

    fun setTemperature(temperature: String) {
        this.temperature = temperature
    }

    fun setTemperatureFeelsLike(temperatureFeelsLike: String) {
        this.temperatureFeelsLike = temperatureFeelsLike
    }

    fun setPressure(pressure: String) {
        this.pressure = pressure
    }

    fun setHumidity(humidity: String) {
        this.humidity = humidity
    }

}