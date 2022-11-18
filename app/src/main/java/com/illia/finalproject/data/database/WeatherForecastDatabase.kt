package com.illia.finalproject.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.illia.finalproject.model.WeatherForecast
import com.illia.finalproject.ui.activity.ApplicationContextHolder

@Database(entities = [WeatherForecast::class], version = 1)
abstract class WeatherForecastDatabase : RoomDatabase() {

    abstract fun forecastDAO () : WeatherForecastDAO

    companion object{
        private var instance : WeatherForecastDatabase? = null
        fun getInstance () : WeatherForecastDatabase {
            if(instance != null){
                return instance as WeatherForecastDatabase
            }else{
                synchronized(this){
                    instance = Room.databaseBuilder(
                        ApplicationContextHolder.getContext(),
                        WeatherForecastDatabase::class.java, "forecast-database").build()
                }
                return instance as WeatherForecastDatabase
            }
        }
    }
}