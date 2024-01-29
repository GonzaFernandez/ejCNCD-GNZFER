package com.example.ejercicio_peliculas.dashboard

import com.example.ejercicio_peliculas.common.toMovie
import com.example.ejercicio_peliculas.dashboard.data.repository.database.MoviesDatabaseRepository
import com.example.ejercicio_peliculas.dashboard.data.repository.database.dao.MoviesDao
import com.example.ejercicio_peliculas.dashboard.data.repository.database.entities.MovieEntity
import com.example.ejercicio_peliculas.dashboard.domain.model.Movie
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
@RunWith(JUnit4::class)
class MoviesDatabaseRepositoryUnitTest {

    @RelaxedMockK
    private lateinit var moviesDao: MoviesDao

    private lateinit var repository: MoviesDatabaseRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        repository = MoviesDatabaseRepository(moviesDao)
    }

    @Test
    fun testGetMoviesFromDb_returnsFlowOfMovies() = runTest {

        val movieEntities = listOf(
            MovieEntity(id = "1", title = "Movie 1","","","","","",""),
            MovieEntity(id = "2", title = "Movie 2","","","","","","")
        )
        val expectedMovies = movieEntities.map { it.toMovie() }
        coEvery { repository.getMoviesFromDb() } returns flowOf(expectedMovies)

        repository.getMoviesFromDb()
        coVerify { repository.getMoviesFromDb() }

    }

    @Test
    fun testInsertMoviesToDb_callsDao() = runTest {
        val movies = listOf(
            Movie(id = "1", title = "Movie 1","","","","","",""),
            Movie(id = "2", title = "Movie 2","","","","","","")
        )
        repository.insertMoviesToDb(movies = movies)
        coVerify { repository.insertMoviesToDb(movies) }
    }

    @Test
    fun testClearAllMoviesFromTable_callsDao() = runTest {
        val movies = listOf(
            Movie(id = "1", title = "Movie 1","","","","","",""),
            Movie(id = "2", title = "Movie 2","","","","","","")
        )
        repository.insertMoviesToDb(movies = movies)
        repository.clearAllMoviesFromTable()
        coVerify { repository.insertMoviesToDb(movies) }
        coVerify { repository.clearAllMoviesFromTable() }
    }

}