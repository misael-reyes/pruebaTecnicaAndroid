package com.developersunited.pruebatecnicaandroid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.developersunited.pruebatecnicaandroid.data.database.dao.MovieDao
import com.developersunited.pruebatecnicaandroid.data.database.entities.MovieEntity

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}