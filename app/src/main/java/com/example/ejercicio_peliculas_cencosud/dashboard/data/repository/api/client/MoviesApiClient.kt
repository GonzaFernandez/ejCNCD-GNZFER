package com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.api.client

import com.example.ejercicio_peliculas_cencosud.BuildConfig
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.api.dtos.MovieListResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApiClient {

    @GET(BuildConfig.FILMS_API_URL)
    suspend fun getFilmsList(): Response<MovieListResponseDto>
}