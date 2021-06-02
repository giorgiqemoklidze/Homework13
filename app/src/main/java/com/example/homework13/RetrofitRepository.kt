package com.example.homework13

import retrofit2.Response
import retrofit2.http.GET


    interface RetrofitRepository {

        @GET("/api/m/v2/news")
        suspend fun getList (): Response<List<NewsModel>>

    }
