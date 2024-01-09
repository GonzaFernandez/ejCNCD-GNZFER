package com.example.ejercicio_peliculas_cencosud.dashboard

import com.example.ejercicio_peliculas_cencosud.common.ApiResponse
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.api.MoviesApiRepository
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.api.client.MoviesApiClient
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.api.dtos.MovieDto
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.api.dtos.MovieListResponseDto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MoviesApiRepositoryUnitTest {

    @RelaxedMockK
    private lateinit var apiServices: MoviesApiClient

    private lateinit var repository: MoviesApiRepository

    @RelaxedMockK
    private lateinit var mockResponseBody: ResponseBody

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        repository = MoviesApiRepository(apiServices)
    }

    @Test
    fun `getFilmsList should return success response when api call is successful`() = runTest {
        val apiMovies = listOf(
            MovieDto("1", "Movie 1", "","","20 Jan 2023","","",""),
            MovieDto("2", "Movie 2", "","","21 Jan 2023","","","")
        )
        val mockResponse = Response.success(MovieListResponseDto(items = emptyList(), errorMessage = ""))
        coEvery { apiServices.getFilmsList() } returns mockResponse

        val result = repository.getFilmsList()
        assertEquals(ApiResponse.Success(mockResponse.body()), result)
    }

    @Test
    fun `getFilmsList should return error response when api call fails`() = runTest {
        val mockErrorResponse = Response.error<MovieListResponseDto>(404, mockResponseBody)
        coEvery {apiServices.getFilmsList() } returns mockErrorResponse
        val result = repository.getFilmsList()
        assertEquals(ApiResponse.Error<MovieListResponseDto>(mockErrorResponse.message()), result)
    }

}