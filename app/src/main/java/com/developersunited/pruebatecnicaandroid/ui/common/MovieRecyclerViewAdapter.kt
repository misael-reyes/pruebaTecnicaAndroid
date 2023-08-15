package com.developersunited.pruebatecnicaandroid.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import androidx.recyclerview.widget.RecyclerView
import com.developersunited.pruebatecnicaandroid.R

class MovieRecyclerViewAdapter(
    private val movieList: List<Movie>,
    private val onClickListener: (Movie) -> Unit
): RecyclerView.Adapter<MovieViewHolder>() {

    private var newList: List<Movie> = movieList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int = newList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = newList[position]
        holder.render(item, onClickListener)
    }

    fun newList(list: List<Movie>) {
        newList = newList + list
    }
}