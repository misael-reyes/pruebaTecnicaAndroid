package com.developersunited.pruebatecnicaandroid.ui.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.developersunited.pruebatecnicaandroid.databinding.MovieItemBinding
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = MovieItemBinding.bind(view)

    fun render(item: Movie, onClickListener: (Movie) -> Unit) {
        binding.tvTitleMovie.text = item.title
        val imageUrl = "https://image.tmdb.org/t/p/w500${item.poster_path}"
        Picasso.get().load(imageUrl).into(binding.ivMovie)
        itemView.setOnClickListener { onClickListener(item) }
    }
}