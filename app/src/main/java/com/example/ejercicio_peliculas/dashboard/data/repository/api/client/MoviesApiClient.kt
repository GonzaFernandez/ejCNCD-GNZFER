package com.example.ejercicio_peliculas.dashboard.data.repository.api.client

import com.example.ejercicio_peliculas.dashboard.data.repository.api.dtos.MovieListResponseDto
import com.example.ejercicio_peliculas_cencosud.BuildConfig
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApiClient {

    @GET("${BuildConfig.FILMS_API_URL}movies.json?key=cb03b960")
    suspend fun getFilmsList(): Response<MovieListResponseDto>
}