package com.illia.finalproject.model

import java.io.Serializable


class WeatherForecastDTO  (
    var overallState: String,
    var timePeriod: String,
    var image: String
        ): Serializable {
}