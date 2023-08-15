package com.developersunited.pruebatecnicaandroid.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import com.developersunited.pruebatecnicaandroid.domain.useCase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetMoviesUseCase
): ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _newMovies = MutableLiveData<List<Movie>>()
    val newMovies: LiveData<List<Movie>> get() = _newMovies

    fun getMovies(page: Int) {
        viewModelScope.launch {
            val response = useCase(true, page)
            _movies.value = response
        }
    }

    fun getNewMovies(page: Int) {
        viewModelScope.launch {
            val response = useCase(true, page)
            _newMovies.value = response
        }
    }
}