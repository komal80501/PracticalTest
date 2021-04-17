package com.komalmane.practicalTest.retrofit


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    val getClient = Retrofit.Builder()
        .baseUrl("http://api.exchangeratesapi.io/")
        .addConverterFactory(ApiWorker.gsonConverter)
        .client(ApiWorker.client)
        .build()
        .create(ApiInterface::class.java)
}