package com.example.moviedb.api

import com.example.moviedb.models.Movie
import com.example.moviedb.models.ResponseItems
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular/")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): ResponseItems<Movie>
}