package com.komalmane.practicalTest.model

data class HistoricalDataResponse(
    val disclaimer: String,
    val license: String,
    val timestamp: Long,
    val base: String,
    val rates: Map<String, Double>
)
