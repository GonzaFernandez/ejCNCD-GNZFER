package com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database

import com.example.ejercicio_peliculas_cencosud.common.toMovie
import com.example.ejercicio_peliculas_cencosud.common.toMovieEntity
import com.example.ejercicio_peliculas_cencosud.dashboard.domain.model.Movie
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database.dao.MoviesDao
import javax.inject.Inject

class MoviesDatabaseRepository @Inject constructor(
    private val moviesDao: MoviesDao
) {
    suspend fun getMoviesFromDb(): List<Movie> {
        return moviesDao.getAllMovie().map { it.toMovie() }
    }

    suspend fun insertMoviesToDb(movies: List<Movie>) {
        moviesDao.insertMovies(movies.map { it.toMovieEntity() })
    }

    suspend fun clearAllMoviesFromTable() {
        moviesDao.clearAllMoviesFromTable()
    }
}