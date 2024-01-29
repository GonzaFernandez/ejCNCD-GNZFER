package com.example.ejercicio_peliculas.dashboard

import com.example.ejercicio_peliculas.common.ApiResponse
import com.example.ejercicio_peliculas.common.DatesUtil.simpleDateConverter
import com.example.ejercicio_peliculas.common.toMovie
import com.example.ejercicio_peliculas.dashboard.data.repository.api.MoviesApiRepository
import com.example.ejercicio_peliculas.dashboard.data.repository.api.dtos.MovieDto
import com.example.ejercicio_peliculas.dashboard.data.repository.api.dtos.MovieListResponseDto
import com.example.ejercicio_peliculas.dashboard.data.repository.database.MoviesDatabaseRepository
import com.example.ejercicio_peliculas.dashboard.domain.GetMoviesUseCase
import com.example.ejercicio_peliculas.dashboard.domain.model.Movie
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetMoviesUseCaseUnitTest {

    @RelaxedMockK
    private lateinit var apiRepository: MoviesApiRepository
    @RelaxedMockK
    private lateinit var dbRepository: MoviesDatabaseRepository

    private lateinit var useCase: GetMoviesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        useCase = GetMoviesUseCase(apiRepository, dbRepository)
    }

    @Test
    fun `invoke with internet on Api and success response`() = runBlocking {
        val apiMovies = listOf(
            MovieDto("1", "Movie 1", "","","20 Jan 2023","","",""),
            MovieDto("2", "Movie 2", "","","21 Jan 2023","","","")
        )
        coEvery {apiRepository.getFilmsList() } returns ApiResponse.Success(data = MovieListResponseDto(items = apiMovies, ""))

        val result = useCase.invoke(isInternetOn = true).first()

        coVerify(exactly = 1) {apiRepository.getFilmsList() }
        coVerify(exactly = 1) {dbRepository.clearAllMoviesFromTable() }
        assertEquals(apiMovies.sortedByDescending { simpleDateConverter(it.releaseState) }.map { it.toMovie() }, result)
    }


    @Test
    fun `invoke with internet on and API error fetches movies from database`() = runTest {
        val dbMovies = listOf(Movie("3", "Movie 3", "", "","20 Jan 2023", "", "" , ""))
        coEvery { apiRepository.getFilmsList() } returns ApiResponse.Error("")
        coEvery { dbRepository.getMoviesFromDb() } returns flowOf(dbMovies)

        val result = useCase.invoke(isInternetOn = true).first()

        coVerify { dbRepository.getMoviesFromDb() }
        assertEquals(dbMovies, result)
    }

}