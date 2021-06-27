package com.example.moviedb.models

import com.squareup.moshi.Json

data class ResponseItems<T>(
    @Json(name = "results") val results: List<T>
)
