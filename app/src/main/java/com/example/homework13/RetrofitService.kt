package com.example.homework13

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val Url = "http://139.162.207.17"

    fun RetrofitService() : RetrofitRepository{

        return Retrofit.Builder().baseUrl(Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitRepository::class.java)
    }
}