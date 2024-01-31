package com.example.ejercicio_peliculas.bottom_menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ejercicio_peliculas.bottom_menu.BottomMenuUtil.getMenuList
import com.example.ejercicio_peliculas.ui.theme.CardBackgroundColor
import com.example.ejercicio_peliculas.ui.theme.GeneralBackgroundColor

@Composable
fun BottomNavigationMenu() {
    var selectedIndex by remember { mutableIntStateOf( 0) }
    val interactionSource = remember { MutableInteractionSource() }
    NavigationBar(containerColor = GeneralBackgroundColor, contentColor = Color.White ) {
        getMenuList().forEachIndexed { index, bottomMenuItem ->
            val selected = selectedIndex == index
            NavigationBarItem(
                selected = selected,
                onClick = { selectedIndex = index },
                icon = { BottomMenuItemIcon(selectedIcon = bottomMenuItem.selectedIcon, unselectedIcon = bottomMenuItem.unselectedIcon, description = bottomMenuItem.description, selected) },
                label = { BottomMenuLabel(bottomMenuItem.label) },
                alwaysShowLabel = false,
                interactionSource = interactionSource, modifier = Modifier.clickable {  })
        }
    }
}

@Composable
fun BottomMenuItemIcon(selectedIcon: ImageVector, unselectedIcon: ImageVector ,description:String, isSelected: Boolean) {
    if (isSelected) {
        Icon(imageVector = selectedIcon, contentDescription = description, tint = CardBackgroundColor)
    } else {
        Icon(imageVector = unselectedIcon, contentDescription = description, tint = CardBackgroundColor)
    }
}

@Composable
fun BottomMenuLabel(textContent:String) {
    Text(text = textContent, color = Color.White)
}

