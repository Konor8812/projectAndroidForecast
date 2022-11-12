package com.illia.finalproject.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit

class MyInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()


        val logStringRequest = "Intercepted on request, headers | ${request.headers} |, url | ${request.url} |"
        Log.d("Retrofit", logStringRequest)

        val response = chain.proceed(request)

        val logStringResponse = "Intercepted on response, headers | ${response.headers} |, body | ${response.body} |"

        Log.d("Retrofit", logStringResponse)
        return response
    }
}