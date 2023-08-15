package com.developersunited.pruebatecnicaandroid.data.repository

import android.util.Log
import com.developersunited.pruebatecnicaandroid.data.database.MovieLocalDataSource
import com.developersunited.pruebatecnicaandroid.data.database.entities.MovieEntity
import com.developersunited.pruebatecnicaandroid.data.network.MovieRemoteDataSource
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieRemoteDataSource,
    private val dao: MovieLocalDataSource
) {

    // PETICIONES A LA API

    suspend fun getMoviesFromApi(page: Int): List<Movie> {
        val response = api.getMovies(page)
        Log.i("prueba", response.toString())
        return response.results.map {
            it.toMovie()
        }
    }

    // PETICIONES A LA BASE DE DATOS LOCAL

    suspend fun getMoviesFromDatabase(): List<Movie> {
        val response: List<MovieEntity> = dao.getMovies()
        return response.map {
            it.toMovie()
        }
    }

    suspend fun insertMovieToDatabase(movie: Movie) {
        dao.insertMovie(movie.toMovieEntity())
    }

    suspend fun deleteMovieToDatabase(movie: Movie) {
        dao.deleteMovie(movie.toMovieEntity())
    }

    suspend fun getMoviesByTitleToDatabase(query: String): List<Movie>? {
        val response = dao.getMoviesByTitle(query)
        return response?.map {
            it.toMovie()
        }
    }

    suspend fun getMoviesByIdToDatabase(id: Long): Movie? {
        return dao.getMovieById(id)?.toMovie()
    }
}