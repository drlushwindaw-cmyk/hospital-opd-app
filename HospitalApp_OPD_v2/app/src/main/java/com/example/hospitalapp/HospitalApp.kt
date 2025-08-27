package com.example.hospitalapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hospitalapp.components.AppBottomBar
import com.example.hospitalapp.components.AppTopBar
import com.example.hospitalapp.nav.AppNavHost
import com.example.hospitalapp.nav.Screen
import com.example.hospitalapp.viewmodel.HospitalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalApp(vm: HospitalViewModel = viewModel()) {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Home,
        Screen.OPD,
        Screen.Patients,
        Screen.Reports
    )
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Screen.Home.route

    Scaffold(
        topBar = { AppTopBar(currentRoute) },
        bottomBar = {
            AppBottomBar(
                currentRoute = currentRoute,
                items = items,
                onNavigate = { navController.navigate(it.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }}
            )
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            vm = vm
        )
    }
}
