package com.example.hospitalapp.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.hospitalapp.nav.Screen

@Composable
fun AppBottomBar(
    currentRoute: String,
    items: List<Screen>,
    onNavigate: (Screen) -> Unit
) {
    NavigationBar(containerColor = Color(0xFFE3F2FD)) { // light blue bar
        items.forEach { screen ->
            val selected = currentRoute == screen.route
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(screen) },
                icon = {
                    if (screen.icon != null) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.label,
                            tint = if (selected) Color(0xFF0D47A1) else Color(0xFF607D8B) // deep blue selected, grey unselected
                        )
                    }
                },
                label = {
                    Text(
                        screen.label,
                        color = if (selected) Color(0xFF0D47A1) else Color(0xFF607D8B)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFFBBDEFB) // selected pill
                )
            )
        }
    }
}
