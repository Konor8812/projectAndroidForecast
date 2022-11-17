package com.illia.finalproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.illia.finalproject.model.WeatherForecast


@Dao
interface WeatherForecastDAO {

    @Query("select * from weatherforecast")
    fun getAll() : List<WeatherForecast>

    @Insert
    fun insert(vararg items: WeatherForecast)

}