package com.developersunited.pruebatecnicaandroid.di

import android.content.Context
import androidx.room.Room
import com.developersunited.pruebatecnicaandroid.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "movies_database").build()

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()
}