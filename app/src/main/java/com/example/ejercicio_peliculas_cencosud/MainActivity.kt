package com.example.ejercicio_peliculas_cencosud

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ejercicio_peliculas_cencosud.common.NetworkUtils.isInternetAvailable
import com.example.ejercicio_peliculas_cencosud.dashboard.ui.DashboardTitle
import com.example.ejercicio_peliculas_cencosud.dashboard.ui.DashboardViewModel
import com.example.ejercicio_peliculas_cencosud.dashboard.ui.MoviesLazyColumnComposable
import com.example.ejercicio_peliculas_cencosud.ui.theme.GeneralBackgroundColor
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
                    Column(modifier = Modifier.background(GeneralBackgroundColor)) {
                        DashboardTitle()
                        MoviesLazyColumnComposable(dashboardViewModel){}
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
