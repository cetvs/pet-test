package com.example.myapplication.data.source.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    var retrofit : Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var simpleApi = retrofit.create(SimpleApi::class.java)

    val api : SimpleApi by lazy{
        retrofit.create(SimpleApi::class.java)
    }
}