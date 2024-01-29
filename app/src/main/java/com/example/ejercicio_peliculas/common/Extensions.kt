package com.example.ejercicio_peliculas.common

import com.example.ejercicio_peliculas.dashboard.domain.model.Movie
import com.example.ejercicio_peliculas.dashboard.data.repository.api.dtos.MovieDto
import com.example.ejercicio_peliculas.dashboard.data.repository.database.entities.MovieEntity

fun MovieEntity.toMovie(): Movie {
    return Movie(id,title, fullTitle, year, releaseState, image, stars, plot)
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(id, title, fullTitle, year, releaseState, image, stars, plot)
}

fun MovieDto.toMovie(): Movie {
    return Movie(id,title, fullTitle, year, releaseState, image, stars, plot)
}