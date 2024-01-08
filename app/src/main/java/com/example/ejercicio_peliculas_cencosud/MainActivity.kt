package com.example.ejercicio_peliculas_cencosud

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio_peliculas_cencosud.common.NetworkUtils.isInternetAvailable
import com.example.ejercicio_peliculas_cencosud.dashboard.ui.DashboardView
import com.example.ejercicio_peliculas_cencosud.dashboard.ui.DashboardViewModel
import com.example.ejercicio_peliculas_cencosud.dashboard.ui.MovieDescriptionScaffold
import com.example.ejercicio_peliculas_cencosud.navigation.Routes
import com.example.ejercicio_peliculas_cencosud.ui.theme.MainAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            dashboardViewModel.getMoviesFromRepository(isInternetAvailable(this@MainActivity))
        }
        setContent {
            MainAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = "Dashboard"){
                        composable(Routes.Dashboard.route) {
                            DashboardView(dashboardViewModel, navigationController)
                        }
                        composable(Routes.MovieDescription.route) {
                            MovieDescriptionScaffold(dashboardViewModel, navigationController)
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }
}
