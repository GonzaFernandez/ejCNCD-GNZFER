package com.example.ejercicio_peliculas_cencosud.dashboard.domain

import com.example.ejercicio_peliculas_cencosud.common.ApiResponse
import com.example.ejercicio_peliculas_cencosud.common.toMovie
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.api.MoviesApiRepository
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database.MoviesDatabaseRepository
import com.example.ejercicio_peliculas_cencosud.dashboard.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val apiRepository: MoviesApiRepository,
    private val dbRepository: MoviesDatabaseRepository
) {
    suspend fun invoke(isInternetOn: Boolean): Flow<List<Movie>?> {
        if (isInternetOn) {
            return when (val response = apiRepository.getFilmsList()) {
                is ApiResponse.Success -> {
                    val films = response.data?.items?.map { it.toMovie() }
                    films?.let {
                        dbRepository.clearAllMoviesFromTable()
                        dbRepository.insertMoviesToDb(it)
                    }
                    flow {
                        emit(films)
                    }
                }
                is ApiResponse.Error -> {
                    dbRepository.getMoviesFromDb()
                }
            }
        } else {
            return dbRepository.getMoviesFromDb()
        }
    }

}