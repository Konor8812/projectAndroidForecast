package com.illia.finalproject

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        testInternet()
    }

    private fun testInternet(){
        println("testInternet")
        val retrofit = MyRetrofitClient.getInstance()

        val apiApi = retrofit.create(ApiApi::class.java)

        lifecycleScope.launch {
            val response  = apiApi.getSmth()
            if (response.isSuccessful){
                println(response.headers())
                println(response.message())
            }else {
                println("error")
            }
        }

    }

}