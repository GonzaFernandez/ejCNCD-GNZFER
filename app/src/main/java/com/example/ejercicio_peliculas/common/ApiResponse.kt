package com.example.ejercicio_peliculas.common

sealed interface ApiResponse<T> {
    data class Success<T>(val data: T): ApiResponse<T>
    data class Error<T>(val code: Int, val reason: String): ApiResponse<T>
}