package com.kotlin.retrofit_rxjava.retrofit

import com.kotlin.retrofit_rxjava.model.RequestWeather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current?access_key=53ba34723e85c0269086f8587407f3f4")
    fun fetchWearther(@Query("query")city:String):Single<RequestWeather>
}