package com.example.ejercicio_peliculas_cencosud.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.ejercicio_peliculas_cencosud.dashboard.domain.model.Movie
import com.example.ejercicio_peliculas_cencosud.ui.theme.CardBackgroundColor


@Composable
fun DashboardTitle() {
    Text(
        text = "Listado de peliculas",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun MoviesLazyColumnComposable(dashboardViewModel: DashboardViewModel, onMovieClicked:(Movie) -> Unit) {
    val recyclerState = rememberLazyGridState()
    val movies = dashboardViewModel.movieListFlow.collectAsState(initial = emptyList()).value
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Center,
            state = recyclerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            content = {
                items(items = movies) { movie ->
                    MovieComposable(movie) {
                        onMovieClicked(it)
                    }
                }
            })
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieComposable(movie: Movie, onItemSelected:(Movie) -> Unit) {
    Card(modifier = Modifier
        .width(250.dp)
        .padding(10.dp)
        .clickable { onItemSelected(movie) }) {
        Column(modifier = Modifier.background(CardBackgroundColor)) {
            GlideImage(
                model = movie.image,
                contentDescription = movie.fullTitle,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )//titulo, release state ordenados por fecha de estreno
            Text(text = movie.fullTitle,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(8.dp),
            color = Color.White)
            Text(text = "Fecha de lanzamiento: ${movie.releaseState}",
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(8.dp),
                color = Color.White)
        }
    }
}

@Composable
fun SpinnerIndicator(dashboardViewModel: DashboardViewModel){
    val showLoader: Boolean by dashboardViewModel.showLoader.observeAsState(false)
    if (showLoader) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Center
        ) {
            CircularProgressIndicator()
        }
    }
}