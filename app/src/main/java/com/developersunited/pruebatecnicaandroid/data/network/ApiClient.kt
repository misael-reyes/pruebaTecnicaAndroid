package com.developersunited.pruebatecnicaandroid.data.network

import com.developersunited.pruebatecnicaandroid.R
import com.developersunited.pruebatecnicaandroid.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "es-MX",
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("api_key") api_key: String = "284e852b82c9ce564fdb84593cac2979"
    ) : MovieResponse
}