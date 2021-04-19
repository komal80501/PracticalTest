package com.komalmane.practicalTest.retrofit

import com.komalmane.practicalTest.model.HistoricalDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun getHistoricalData(@Url url: String): Call<HistoricalDataResponse>
}
