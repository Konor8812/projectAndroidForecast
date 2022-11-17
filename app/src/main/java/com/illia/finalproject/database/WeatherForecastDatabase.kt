package com.illia.finalproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.illia.finalproject.model.WeatherForecast

@Database(entities = [WeatherForecast::class], version = 1)
abstract class WeatherForecastDatabase : RoomDatabase() {
    abstract fun forecastDAO () : WeatherForecastDAO
}