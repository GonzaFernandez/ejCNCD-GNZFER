package com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ejercicio_peliculas_cencosud.common.Constants.FILMS_DATABASE_TABLE
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database.entities.MovieEntity

@Dao
interface MoviesDao {
    @Query("Select * from $FILMS_DATABASE_TABLE order by releaseState DESC")
    suspend fun getAllMovie():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("Delete from $FILMS_DATABASE_TABLE")
    suspend fun clearAllMoviesFromTable()
}