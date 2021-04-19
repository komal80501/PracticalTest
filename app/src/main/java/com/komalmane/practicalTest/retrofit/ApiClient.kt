package com.komalmane.practicalTest.retrofit


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {

    val baseUrl = "https://openexchangerates.org/api/"

    private val client = OkHttpClient.Builder().build()

    val getClient = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ApiInterface::class.java)
}