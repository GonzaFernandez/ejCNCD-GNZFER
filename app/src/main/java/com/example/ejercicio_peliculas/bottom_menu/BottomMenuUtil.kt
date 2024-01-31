package com.example.ejercicio_peliculas.bottom_menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search

object BottomMenuUtil {
    fun getMenuList(): List<BottomMenuItem> = listOf(
        BottomMenuItem(label = "Home", description = "Home", selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home, false),
        BottomMenuItem(label = "Search", description = "Search", selectedIcon = Icons.Filled.Search, unselectedIcon = Icons.Outlined.Search, false),
        BottomMenuItem(label = "Profile", description = "Profile", selectedIcon = Icons.Filled.Person, unselectedIcon = Icons.Outlined.Person, false),
        BottomMenuItem(label = "Menu", description = "Menu", selectedIcon = Icons.Filled.Menu, unselectedIcon = Icons.Outlined.Menu, false)
    )
}