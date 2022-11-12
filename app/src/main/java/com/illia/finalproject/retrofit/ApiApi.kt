package com.illia.finalproject.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface ApiApi {
    @GET("/android/helper/value")
        suspend fun getSmth() : Response<Something>
}