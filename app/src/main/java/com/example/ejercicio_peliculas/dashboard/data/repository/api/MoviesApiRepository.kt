package com.example.ejercicio_peliculas.dashboard.data.repository.api

import com.example.ejercicio_peliculas.common.ApiResponse
import com.example.ejercicio_peliculas.dashboard.data.repository.api.client.MoviesApiClient
import com.example.ejercicio_peliculas.dashboard.data.repository.api.dtos.MovieListResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MoviesApiRepository @Inject constructor(private val apiServices: MoviesApiClient) {

    private suspend fun <T> process(block: suspend () -> Response<T>) : ApiResponse<T?> {
        return try {
            withContext(Dispatchers.IO) {
                val response = block()
                if (response.isSuccessful) {
                    ApiResponse.Success(response.body())
                } else {
                    ApiResponse.Error(response.code(), response.message())
                }
            }
        } catch (e: Exception) {
            ApiResponse.Error(0, e.message!!)
        }
    }

    suspend fun getFilmsList(): ApiResponse<MovieListResponseDto?> {
       return process {
            apiServices.getFilmsList()
        }
    }
}