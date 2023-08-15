package com.developersunited.pruebatecnicaandroid.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersunited.pruebatecnicaandroid.domain.model.Movie
import com.developersunited.pruebatecnicaandroid.domain.useCase.GetMovieByIdUseCase
import com.developersunited.pruebatecnicaandroid.domain.useCase.InsertDeleteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val useCase: InsertDeleteMovieUseCase,
    private val useCaseSave: GetMovieByIdUseCase
) : ViewModel() {

    private val _isInsert = MutableLiveData<Boolean>()
    val isInsert: LiveData<Boolean> get() = _isInsert

    fun saveMovie(movie: Movie) {
        viewModelScope.launch {
            val response = useCase(movie)
            _isInsert.value = response
        }
    }

    private val _isSave = MutableLiveData<Boolean>()
    val isSave: LiveData<Boolean> get() = _isSave

    fun isSave(id: Long) {
        viewModelScope.launch {
            val response = useCaseSave(id)
            _isSave.value = response
        }
    }
}