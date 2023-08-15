package com.developersunited.pruebatecnicaandroid.ui.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import com.developersunited.pruebatecnicaandroid.domain.useCase.GetMovieByTitleUseCase
import com.developersunited.pruebatecnicaandroid.domain.useCase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalMoviesViewModel @Inject constructor(
    private val useCase: GetMoviesUseCase,
    private val useCaseTitle: GetMovieByTitleUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun getMoviesLocal() {
        viewModelScope.launch {
            val response = useCase(false, 0)
            _movies.value = response
        }
    }

    fun findMovieByTitle(title: String) {
        viewModelScope.launch {
            _movies.value = emptyList()
            //_isLoading.value = true
            val response = useCaseTitle(title)
            //_isLoading.value = false
            _movies.value = response
        }
    }

}