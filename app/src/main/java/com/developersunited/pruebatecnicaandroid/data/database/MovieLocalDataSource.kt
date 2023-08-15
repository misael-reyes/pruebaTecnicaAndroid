package com.developersunited.pruebatecnicaandroid.data.database

import com.developersunited.pruebatecnicaandroid.data.database.dao.MovieDao
import com.developersunited.pruebatecnicaandroid.data.database.entities.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend fun getMovies(): List<MovieEntity> {
        return withContext(Dispatchers.IO) {
            movieDao.getMovies()
        }
    }

    suspend fun insertMovie(movie: MovieEntity) {
        withContext(Dispatchers.IO) {
            movieDao.insertMovie(movie)
        }
    }

    suspend fun deleteMovie(movie: MovieEntity) {
        withContext(Dispatchers.IO) {
            movieDao.deleteMovie(movie)
        }
    }

    suspend fun getMoviesByTitle(query: String): List<MovieEntity>? {
        return withContext(Dispatchers.IO) {
            movieDao.getMovieByTitle(query)
        }
    }

    suspend fun getMovieById(id: Long): MovieEntity? {
        return withContext(Dispatchers.IO) {
            movieDao.getMovieById(id)
        }
    }
}