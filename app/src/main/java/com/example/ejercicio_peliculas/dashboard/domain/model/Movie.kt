package com.example.ejercicio_peliculas.dashboard.domain.model

data class Movie(
    val id: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val releaseState: String,
    val image: String,
    val stars: String,
    val plot: String
)
