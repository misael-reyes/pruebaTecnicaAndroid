package com.developersunited.pruebatecnicaandroid.domain.useCase

import com.developersunited.pruebatecnicaandroid.data.repository.MovieRepository
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import javax.inject.Inject

class InsertDeleteMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movie: Movie): Boolean {
        val mov  = repository.getMoviesByIdToDatabase(movie.id)
        return if (mov == null) {
            repository.insertMovieToDatabase(movie)
            true
        } else {
            repository.deleteMovieToDatabase(movie)
            false
        }
    }
}