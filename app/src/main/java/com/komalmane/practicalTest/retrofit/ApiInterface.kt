package com.komalmane.practicalTest.retrofit

import com.komalmane.practicalTest.model.LatestResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    /*@GET("/history")
    suspend fun getList(
        @Query("start_at") start_at: String,
        @Query("end_at") end_at: String,
        @Query("base") base: String
    ): ListingResponse*/


    @GET("v1/latest")
    suspend fun getLatest(
        @Query("access_key") access_key: String,
        @Query("symbols") symbols: String,
        @Query("format") format: String
    ): LatestResponse

}
