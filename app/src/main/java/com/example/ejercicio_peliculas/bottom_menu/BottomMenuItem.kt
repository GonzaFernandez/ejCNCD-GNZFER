package com.example.ejercicio_peliculas.bottom_menu

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomMenuItem(
    val label:String,
    val description:String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val isSelected: Boolean
)
