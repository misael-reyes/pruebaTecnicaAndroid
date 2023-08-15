package com.developersunited.pruebatecnicaandroid.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developersunited.pruebatecnicaandroid.R
import com.developersunited.pruebatecnicaandroid.databinding.ActivityMainBinding
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import com.developersunited.pruebatecnicaandroid.ui.common.MovieRecyclerViewAdapter
import com.developersunited.pruebatecnicaandroid.ui.detail.DetailMovieActivity
import com.developersunited.pruebatecnicaandroid.ui.local.LocalMoviesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: GridLayoutManager
    private var currentPage: Int = 1

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        manager = GridLayoutManager(this, 3)

        initObserver()
        initListener()

        viewModel.getMovies(currentPage)
    }

    private fun initObserver() {
        viewModel.movies.observe(this) {
            initRecyclerView(it)
        }

        viewModel.newMovies.observe(this) {
            val adapter = binding.moviesRecyclerView.adapter as MovieRecyclerViewAdapter
            adapter.newList(it)
//
            //// Notifica al adaptador que los datos han cambiado
            adapter.notifyDataSetChanged()
        }
    }

    private fun initListener() {
        binding.btnLocal.setOnClickListener {
            startActivity(Intent(this, LocalMoviesActivity::class.java))
        }

        binding.moviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = manager.childCount
                val totalItemCount = manager.itemCount
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    // Usuario ha llegado al final, cargar más datos
                    currentPage++
                    viewModel.getNewMovies(currentPage)
                    Log.i("cargar", "cargadno pagina $currentPage")
                }
            }
        })
    }

    private fun initRecyclerView(movies: List<Movie>) {

        binding.moviesRecyclerView.layoutManager = manager
        binding.moviesRecyclerView.adapter =
            MovieRecyclerViewAdapter(movies) {
                onItemSelected(it)
            }
    }

    private fun onItemSelected(movie: Movie) {
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}