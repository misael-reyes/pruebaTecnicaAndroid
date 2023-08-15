package com.developersunited.pruebatecnicaandroid.domain.useCase

import com.developersunited.pruebatecnicaandroid.data.repository.MovieRepository
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import javax.inject.Inject

class GetMovieByTitleUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(title: String): List<Movie> {

        return repository.getMoviesByTitleToDatabase(title) ?: emptyList()
    }
}