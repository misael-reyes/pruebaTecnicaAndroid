package com.developersunited.pruebatecnicaandroid.data.network

import com.developersunited.pruebatecnicaandroid.data.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val api: ApiClient
) {
    suspend fun getMovies(page: Int): MovieResponse {
        return withContext(Dispatchers.IO) {
            api.getMovies(page = page)
        }
    }
}