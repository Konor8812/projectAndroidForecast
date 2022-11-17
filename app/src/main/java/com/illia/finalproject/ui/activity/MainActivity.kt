package com.illia.finalproject.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.illia.finalproject.database.WeatherForecastDatabase
import com.illia.finalproject.databinding.ActivityMainBinding
import com.illia.finalproject.ui.fragment.MyViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var txtView: TextView
    private lateinit var doRequestButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        println("start")
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        txtView = binding.txtView
//        doRequestButton = binding.doRequestButton

//        doRequestButton.setOnClickListener{
//            val retrofit = MyRetrofitClient.getInstance()
//            val apiApi = retrofit.create(ApiApi::class.java)
//            lifecycleScope.launch {
//                val response  = apiApi.getForecast()
//                if (response.isSuccessful){
//                    println(response.headers())
//                    var resp = response.body();
//                    println(resp.toString())
//                    txtView.setText(resp?.getForecastForDays(1) ?: "Unresolvable")
//                }else {
//                    println("error")
//                }
//            }
//        }
    }


}