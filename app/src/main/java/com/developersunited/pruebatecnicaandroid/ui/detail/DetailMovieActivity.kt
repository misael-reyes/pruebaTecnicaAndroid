package com.developersunited.pruebatecnicaandroid.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.developersunited.pruebatecnicaandroid.R
import com.developersunited.pruebatecnicaandroid.databinding.ActivityDetailMovieBinding
import com.developersunited.pruebatecnicaandroid.databinding.ActivityMainBinding
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import com.developersunited.pruebatecnicaandroid.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    private val viewModel: DetailMovieViewModel by viewModels()

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = (intent.getSerializableExtra("movie") as? Movie)!!
        showMovie(movie)

        viewModel.isSave(movie.id)

        initListener()
        initObserver()
    }

    private fun showMovie(mov: Movie?) {
        if (mov != null) {
            val imageUrl = "https://image.tmdb.org/t/p/w500${mov.backdrop_path}"
            Picasso.get().load(imageUrl).into(binding.imageMovie)
            binding.title.text = mov.title
            binding.overview.text = mov.overview
            binding.vote.text = mov.vote_average.toString()
        }
    }

    private fun initListener() {
        binding.btn.setOnClickListener {
            viewModel.saveMovie(movie)
        }
    }

    private fun initObserver() {
        viewModel.isInsert.observe(this) {
            if (it) binding.btn.setImageResource(R.drawable.delete_icon)
            else binding.btn.setImageResource(R.drawable.save_icon)
        }

        viewModel.isSave.observe(this) {
            if (it) binding.btn.setImageResource(R.drawable.delete_icon)
            else binding.btn.setImageResource(R.drawable.save_icon)
        }
    }
}