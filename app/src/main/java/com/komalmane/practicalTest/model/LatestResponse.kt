package com.komalmane.practicalTest.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LatestResponse(

    @SerializedName("success") @Expose
    var success: Boolean,

    @SerializedName("base") @Expose
    var base: String,

    @SerializedName("date") @Expose
    var date: String,

    @SerializedName("rates") @Expose
    var rates: JsonObject,
)

