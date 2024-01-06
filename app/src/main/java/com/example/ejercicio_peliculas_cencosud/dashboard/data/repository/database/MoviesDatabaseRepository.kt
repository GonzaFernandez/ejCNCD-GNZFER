package com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database

import com.example.ejercicio_peliculas_cencosud.common.toMovie
import com.example.ejercicio_peliculas_cencosud.common.toMovieEntity
import com.example.ejercicio_peliculas_cencosud.dashboard.domain.model.Movie
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database.dao.MoviesDao
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database.entities.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesDatabaseRepository @Inject constructor(
    private val moviesDao: MoviesDao
) {
    suspend fun getMoviesFromDb(): Flow<List<Movie>> {
         return moviesDao.getAllMovie().map {
             movies: List<MovieEntity> -> movies.map { movie -> movie.toMovie() }
         }
    }

    suspend fun insertMoviesToDb(movies: List<Movie>) {
        moviesDao.insertMovies(movies.map { it.toMovieEntity() })
    }

    suspend fun clearAllMoviesFromTable() {
        moviesDao.clearAllMoviesFromTable()
    }
}