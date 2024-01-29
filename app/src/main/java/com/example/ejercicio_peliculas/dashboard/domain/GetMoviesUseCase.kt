package com.example.ejercicio_peliculas.dashboard.domain

import com.example.ejercicio_peliculas.common.ApiResponse
import com.example.ejercicio_peliculas.common.DatesUtil.simpleDateConverter
import com.example.ejercicio_peliculas.common.toMovie
import com.example.ejercicio_peliculas.dashboard.data.repository.api.MoviesApiRepository
import com.example.ejercicio_peliculas.dashboard.data.repository.database.MoviesDatabaseRepository
import com.example.ejercicio_peliculas.dashboard.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val apiRepository: MoviesApiRepository,
    private val dbRepository: MoviesDatabaseRepository
) {
    suspend fun invoke(isInternetOn: Boolean): Flow<List<Movie>?> {
        if (isInternetOn) {
            return when (val response = apiRepository.getFilmsList()) {
                is ApiResponse.Success -> {
                    val films: List<Movie>? = response.data?.items?.map { it.toMovie() }?.
                    toMutableList()?.sortedByDescending { simpleDateConverter(it.releaseState) }
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