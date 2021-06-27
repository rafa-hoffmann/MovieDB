package com.example.moviedb.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.moviedb.models.Movie
import com.example.moviedb.paging.MoviesRepository

class MovieListViewModel(private val moviesRepository: MoviesRepository = MoviesRepository.getInstance()) : ViewModel() {

    fun getMovies() : LiveData<PagingData<Movie>> {
        return moviesRepository.getPopularMovies().map {
            it.map { movie ->
                movie
            }
        }.cachedIn(viewModelScope)
    }
}