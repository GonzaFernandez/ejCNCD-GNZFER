package com.example.ejercicio_peliculas.dashboard.data.repository.api.dtos

import com.google.gson.annotations.SerializedName

class MovieDto (
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("fullTitle")
    val fullTitle: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("releaseState")
    val releaseState: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("stars")
    val stars: String,
    @SerializedName("plot")
    val plot: String

)