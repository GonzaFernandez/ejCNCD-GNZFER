package com.example.ejercicio_peliculas.navigation

sealed class Routes(val route: String) {
    object Dashboard: Routes("Dashboard")
    object MovieDescription: Routes("MovieDescription")
}
