package com.example.ejercicio_peliculas_cencosud.common

sealed interface ApiResponse<T> {
    data class Success<T>(val data: T): ApiResponse<T>
    data class Error<T>(val reason: String): ApiResponse<T>
}