package com.illia.finalproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.illia.finalproject.data.model.WeatherForecast


@Dao
interface WeatherForecastDAO {

    @Query("select * from weatherforecast where date between :timestamp and :timestamp+:nod " +
            "and latitude between :latitude-1 and :latitude+1 " +
            "and longitude between :longitude-1 and :longitude+1 ")
    fun getForecastFromNow(timestamp:Long, latitude: Double, longitude : Double, nod: Int) : List<WeatherForecast>

    @Insert
    fun insert(vararg items: WeatherForecast)

    @Update
    fun updateEntities(vararg entities: WeatherForecast )
}