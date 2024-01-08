package com.example.ejercicio_peliculas_cencosud.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio_peliculas_cencosud.dashboard.domain.GetMoviesUseCase
import com.example.ejercicio_peliculas_cencosud.dashboard.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val moviesUseCase: GetMoviesUseCase): ViewModel() {

    private val _showLoader = MutableLiveData<Boolean>().apply { postValue(true) }
    val showLoader: LiveData<Boolean> = _showLoader

    private val _movieListFlow = MutableStateFlow(emptyList<Movie>())
    var movieListFlow: StateFlow<List<Movie>> = _movieListFlow

    lateinit var clickedMovie: Movie

    fun getMoviesFromRepository(isInternetConnected: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val movies: Flow<List<Movie>?> = moviesUseCase.invoke(isInternetConnected)
            movies.collect {
                listOfMovies -> listOfMovies?.let { _movieListFlow.emit(it)}
                _showLoader.postValue(false)
            }
        }
    }

}