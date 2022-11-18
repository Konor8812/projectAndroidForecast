package com.illia.finalproject.data.retrofit

import com.illia.finalproject.data.retrofit.interceptor.MyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MyRetrofitClient {


    fun getInstance (): Retrofit{
        val myInterceptor = MyInterceptor()
        val mHttpInterceptor = HttpLoggingInterceptor()
        mHttpInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val mOkHttpClient = OkHttpClient.Builder()
            .addInterceptor(myInterceptor)
            .addInterceptor(mHttpInterceptor)
            .build()

        val retrofit :Retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
        return retrofit
    }

}