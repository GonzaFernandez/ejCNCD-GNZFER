package com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database.dao.MoviesDao
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun getMovieDao(): MoviesDao
}