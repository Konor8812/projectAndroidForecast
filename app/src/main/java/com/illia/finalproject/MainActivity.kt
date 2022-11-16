package com.illia.finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.illia.finalproject.databinding.ActivityMainBinding
import com.illia.finalproject.retrofit.ApiApi
import com.illia.finalproject.retrofit.MyRetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var txtView : TextView
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