package com.developersunited.pruebatecnicaandroid.domain.useCase

import com.developersunited.pruebatecnicaandroid.data.repository.MovieRepository
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(id: Long): Boolean {
        val movie = repository.getMoviesByIdToDatabase(id)
        return movie != null
    }
}