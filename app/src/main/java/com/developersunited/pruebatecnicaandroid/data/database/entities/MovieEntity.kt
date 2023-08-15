package com.developersunited.pruebatecnicaandroid.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.developersunited.pruebatecnicaandroid.domain.model.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double
) {
    fun toMovie(): Movie {
        return Movie(
            backdrop_path = backdropPath,
            id = id,
            overview = overview,
            poster_path = posterPath,
            title = title,
            vote_average = voteAverage
        )
    }
}