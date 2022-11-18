package com.illia.finalproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["date", "latitude", "longitude"])
data class WeatherForecast(
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "date_str")
    val dateAsString: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "overall_state")
    val overallState: String,
    @ColumnInfo(name = "image_url")
    val image: String,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
    @ColumnInfo(name = "longitude")
    val longitude: Double,
    @ColumnInfo(name = "forecast_for_day")
    val serializedForecast: String
)