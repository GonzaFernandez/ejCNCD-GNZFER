package com.example.ejercicio_peliculas_cencosud.common

import com.example.ejercicio_peliculas_cencosud.dashboard.domain.model.Movie
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.api.dtos.MovieDto
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.database.entities.MovieEntity

fun MovieEntity.toMovie(): Movie {
    return Movie(id,title, fullTitle, year, releaseState, image, stars)
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(id, title, fullTitle, year, releaseState, image, stars)
}

fun MovieDto.toMovie(): Movie {
    return Movie(id,title, fullTitle, year, releaseState, image, stars)
}