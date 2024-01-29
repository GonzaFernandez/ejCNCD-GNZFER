package com.example.ejercicio_peliculas.common

import com.example.ejercicio_peliculas.dashboard.data.repository.api.dtos.MovieDto
import com.example.ejercicio_peliculas.dashboard.data.repository.database.entities.MovieEntity
import com.example.ejercicio_peliculas.dashboard.domain.model.Movie
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ExtensionsUnitTest {
    @Test
    fun movieEntity_toMovie_test() {
        val movieEntity = MovieEntity("1","titulo","fullTitle","2024","20 Jan 2024","url of image", "stars", "plot")
        val result = movieEntity.toMovie()
        assertTrue(result is Movie)
        assertTrue(result.id == "1")
    }
    @Test
    fun movie_toMovieEntity_test() {
        val movie= Movie("1","titulo","fullTitle","2024","20 Jan 2024","url of image", "stars", "plot")
        val result = movie.toMovieEntity()
        assertTrue(result is MovieEntity)
        assertTrue(result.id == "1")
    }
    @Test
    fun movieDto_toMovie_test() {
        val movie= MovieDto("1","titulo","fullTitle","2024","20 Jan 2024","url of image", "stars", "plot")
        val result = movie.toMovie()
        assertTrue(result is Movie)
        assertTrue(result.id == "1")
    }
}