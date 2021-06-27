package com.example.moviedb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviedb.api.Constants
import com.example.moviedb.databinding.FragmentMovieDetailBinding
import com.example.moviedb.models.Movie
import com.squareup.picasso.Picasso

class MovieDetailsFragment : Fragment() {
    companion object {
        const val MOVIE = "movie"
    }

    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(MOVIE)) {
                movie = it.getParcelable(MOVIE)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        movie?.let {
            binding.movie = it
            Picasso.get().load(Constants.IMAGE_URL + it.backdropPath).into(binding.header);
        }

        return rootView
    }

}