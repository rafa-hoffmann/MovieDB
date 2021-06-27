package com.example.moviedb.paging

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.moviedb.api.MovieService
import com.example.moviedb.api.RetrofitInstance
import com.example.moviedb.models.Movie

class MoviesRepository (private val movieService: MovieService = RetrofitInstance.getMovieService()) {
    companion object {
        const val PAGE_SIZE = 25
        fun getInstance() = MoviesRepository()
    }

    fun getPopularMovies(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service = movieService)
            }
        ).liveData
    }
}