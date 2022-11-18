package com.illia.finalproject.data.model

import java.io.Serializable


data class WeatherForecastDTO  (
    var overallState: String,
    var timePeriod: String,
    var image: String,
    var city : String?,
    var fullDescription:String
        ): Serializable