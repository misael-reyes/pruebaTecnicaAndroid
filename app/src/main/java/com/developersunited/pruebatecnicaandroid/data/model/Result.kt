package com.developersunited.pruebatecnicaandroid.data.model

import com.developersunited.pruebatecnicaandroid.domain.model.Movie

data class Result (
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Long>,
    val id: Long,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long
) {
    fun toMovie(): Movie {
        return Movie(
            backdrop_path = backdrop_path ?: "/uMSxXLfH7v30gRNBqsQaSP3yqX5.jpg",
            id = id,
            overview = overview,
            poster_path = poster_path,
            title = title,
            vote_average = vote_average
        )
    }
}