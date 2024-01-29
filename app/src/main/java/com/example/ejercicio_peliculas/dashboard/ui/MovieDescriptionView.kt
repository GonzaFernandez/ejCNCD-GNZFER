package com.example.ejercicio_peliculas.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.ejercicio_peliculas.navigation.Routes
import com.example.ejercicio_peliculas.ui.theme.CardBackgroundColor
import com.example.ejercicio_peliculas.ui.theme.GeneralBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDescriptionScaffold(
    dashboardViewModel: DashboardViewModel,
    navigationController: NavHostController
) {
    Scaffold(topBar = {MovieDescriptionTopBar(dashboardViewModel, navigationController)}, content = {
            contentPadding -> MovieDescriptionView(dashboardViewModel, contentPadding)
    })
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDescriptionTopBar(dashboardViewModel: DashboardViewModel, navigationController: NavHostController ) {
    TopAppBar(title = { Text(text = dashboardViewModel.clickedMovie.fullTitle, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = {
                navigationController.navigate(Routes.Dashboard.route)
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "go back", tint = Color.White)
            }
        },
    colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = CardBackgroundColor,
        titleContentColor = Color.White))
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieDescriptionView(dashboardViewModel: DashboardViewModel, contentPadding: PaddingValues) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .verticalScroll(rememberScrollState())
            .background(GeneralBackgroundColor)) {
        Text(text = "Movie detail:",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(0.dp, 8.dp),
            color = Color.White)
        Row(Modifier.fillMaxWidth()) {
            GlideImage(
                model = dashboardViewModel.clickedMovie.image,
                contentDescription = dashboardViewModel.clickedMovie.fullTitle,
                modifier = Modifier
                    .height(300.dp)
                    .aspectRatio(9f / 16f)
                    .weight(1f).padding(16.dp, 0.dp),
                alignment = Alignment.Center
            )
            Column(
                Modifier
                    .weight(1f)
                    .padding(8.dp, 24.dp, 8.dp, 0.dp)) {
                Text(text = "Release date: ${dashboardViewModel.clickedMovie.releaseState}", color = Color.White)
                Text(text = "Main cast: ${dashboardViewModel.clickedMovie.stars}", color = Color.White)
            }

        }
        Text(text = "Plot: ${dashboardViewModel.clickedMovie.plot}", color = Color.White, modifier = Modifier.padding(16.dp, 8.dp))
    }

}