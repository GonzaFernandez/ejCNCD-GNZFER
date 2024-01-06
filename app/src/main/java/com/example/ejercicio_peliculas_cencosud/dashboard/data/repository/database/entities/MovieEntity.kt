package com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ejercicio_peliculas_cencosud.common.Constants.FILMS_DATABASE_TABLE

@Entity(tableName = FILMS_DATABASE_TABLE)
data class MovieEntity(
    @PrimaryKey @ColumnInfo("id") val id:String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("fullTitle") val fullTitle: String,
    @ColumnInfo("year") val year: String,
    @ColumnInfo("releaseState") val releaseState: String,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("stars") val stars: String,
    @ColumnInfo("plot") val plot: String
)