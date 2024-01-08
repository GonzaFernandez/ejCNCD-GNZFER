package com.example.ejercicio_peliculas_cencosud.navigation

sealed class Routes(val route: String) {
    object Dashboard: Routes("Dashboard")
    object MovieDescription: Routes("MovieDescription")
}
