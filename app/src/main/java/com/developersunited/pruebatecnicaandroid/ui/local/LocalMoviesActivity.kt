package com.developersunited.pruebatecnicaandroid.ui.local

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.developersunited.pruebatecnicaandroid.R
import com.developersunited.pruebatecnicaandroid.databinding.ActivityLocalMoviesBinding
import com.developersunited.pruebatecnicaandroid.databinding.ActivityMainBinding
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import com.developersunited.pruebatecnicaandroid.ui.common.MovieRecyclerViewAdapter
import com.developersunited.pruebatecnicaandroid.ui.detail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalMoviesActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityLocalMoviesBinding

    private val viewModel: LocalMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObserver()
        initListener()

        viewModel.getMoviesLocal()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMoviesLocal()
    }

    private fun initObserver() {
        viewModel.movies.observe(this) {
            if (it.isNotEmpty()) initRecyclerView(it)
            else Toast.makeText(this, "No se encontraron peliculas", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListener() {
        binding.svMovie.setOnQueryTextListener(this)
    }

    private fun initRecyclerView(movies: List<Movie>) {
        val manager = GridLayoutManager(this, 3)
        binding.movieLocalRecyclerView.layoutManager = manager
        binding.movieLocalRecyclerView.adapter =
            MovieRecyclerViewAdapter(movies) {
                onItemSelected(it)
            }
    }

    private fun onItemSelected(movie: Movie) {
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            viewModel.findMovieByTitle(query)
        }
        //hideKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}