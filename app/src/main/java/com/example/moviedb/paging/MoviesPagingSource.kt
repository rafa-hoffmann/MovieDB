package com.example.moviedb.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviedb.api.MovieService
import com.example.moviedb.models.Movie
import java.io.IOException
import retrofit2.HttpException

class MoviesPagingSource(val service: MovieService) : PagingSource<Int, Movie>() {

    companion object {
        private const val PAGE_START = 1
        private const val API_KEY = "88f4989fca7d27a6890c5d776bc11667"
        private const val LANGUAGE = "pt-BR"
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: PAGE_START
        return try {
            val movies = service.getPopularMovies(
                apiKey = API_KEY,
                language = LANGUAGE,
                page = page
            ).results

            val nextPage = if (movies.isEmpty()) {
                null
            } else {
                page + (params.loadSize / MoviesRepository.PAGE_SIZE)
            }

            LoadResult.Page(
                data = movies,
                prevKey = if (page == PAGE_START) null else page,
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}