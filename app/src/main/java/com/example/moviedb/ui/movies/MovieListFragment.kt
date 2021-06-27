package com.example.moviedb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentMoviesBinding
import com.example.moviedb.models.Movie
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModels()

    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onClickListener = View.OnClickListener { itemView ->
            val item = itemView.tag as Movie
            val bundle = Bundle()
            bundle.putParcelable(
                MovieDetailsFragment.MOVIE,
                item
            )
            itemView.findNavController().navigate(R.id.show_item_detail, bundle)
        }

        val adapter = MovieListAdapter(onClickListener)
        val recyclerView = binding.movieList
        recyclerView.adapter = adapter

        viewModel.getMovies().observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }
}