package com.example.ejercicio_peliculas_cencosud.dashboard.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ejercicio_peliculas_cencosud.dashboard.data.repository.api.dtos.MovieDto
import com.example.ejercicio_peliculas_cencosud.dashboard.domain.GetMoviesUseCase
import com.example.ejercicio_peliculas_cencosud.dashboard.domain.model.Movie
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class DashboardViewModelUnitTest {

    @RelaxedMockK
    private lateinit var moviesUseCase: GetMoviesUseCase

    private lateinit var viewModel: DashboardViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel = DashboardViewModel(moviesUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getMoviesFromRepository returns list of movies when internet is on`() = runTest {
        val movies: List<Movie> = listOf(
            Movie("1", "Movie 1", "","","20 Jan 2023","","",""),
            Movie("2", "Movie 2", "","","21 Jan 2023","","","")
        )
        coEvery { moviesUseCase.invoke(true) } returns flowOf(movies)

        viewModel.getMoviesFromRepository(true)
        val stateFlowData:List<Movie> = viewModel.movieListFlow.first()
        assertTrue(stateFlowData[0].id == movies[0].id)
    }

    @Test
    fun `getMoviesFromRepository returns list of movies when internet is off`() = runTest {
        val movies: List<Movie> = listOf(
            Movie("1", "Movie 1", "","","20 Jan 2023","","",""),
            Movie("2", "Movie 2", "","","21 Jan 2023","","","")
        )
        coEvery { moviesUseCase.invoke(false) } returns flowOf(movies)

        viewModel.getMoviesFromRepository(false)
        val stateFlowData:List<Movie> = viewModel.movieListFlow.first()
        assertTrue(stateFlowData[0].id == movies[0].id)
    }
}