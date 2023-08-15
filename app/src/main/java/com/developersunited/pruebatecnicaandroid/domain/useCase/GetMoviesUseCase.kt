package com.developersunited.pruebatecnicaandroid.domain.useCase

import android.util.Log
import com.developersunited.pruebatecnicaandroid.data.repository.MovieRepository
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(flag: Boolean, page: Int): List<Movie> {
        var movies: List<Movie>? = null
        if (flag) {
            try {
                movies = repository.getMoviesFromApi(page)
            } catch (e: Exception) {
                Log.i("errorconexion",e.message.toString())
            }
            return movies ?: emptyList()
        } else {
            movies = repository.getMoviesFromDatabase()
            return movies
        }
    }
}