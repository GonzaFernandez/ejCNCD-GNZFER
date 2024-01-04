package com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.api.dtos

import com.google.gson.annotations.SerializedName

class MovieListResponseDto (
    @SerializedName("items")
    val items: List<MovieDto>,
    @SerializedName("errorMessage")
    val errorMessage: String
)
