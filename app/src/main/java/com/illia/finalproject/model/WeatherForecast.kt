package com.illia.finalproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherForecast (
    @PrimaryKey
    val date : String,
    @ColumnInfo(name="forecast_for_day")
    val serializedForecast : String
        )