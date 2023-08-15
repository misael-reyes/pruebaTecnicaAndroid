package com.developersunited.pruebatecnicaandroid.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.developersunited.pruebatecnicaandroid.data.database.entities.MovieEntity
import java.io.Serializable

data class Movie (
    val backdrop_path: String,
    val id: Long,
    val overview: String,
    val poster_path: String,
    val title: String,
    val vote_average: Double
): Serializable {
    fun toMovieEntity(): MovieEntity {
        return MovieEntity(
            id = id,
            backdropPath = backdrop_path,
            overview = overview,
            posterPath = poster_path,
            title = title,
            voteAverage = vote_average
        )
    }
}