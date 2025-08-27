package com.example.hospitalapp.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hospitalapp.screens.*
import com.example.hospitalapp.viewmodel.HospitalViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    vm: HospitalViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen(onNavigate = { navController.navigate(it.route) }) }
        composable(Screen.OPD.route) { OPDScreen(vm) }
        composable(Screen.Patients.route) { PatientsScreen(vm) }
        composable(Screen.Appointments.route) { AppointmentsScreen(vm) }
        composable(Screen.Pharmacy.route) { PharmacyScreen(vm) }
        composable(Screen.Reports.route) { ReportsScreen(vm) }
        composable(Screen.Doctors.route) { DoctorsScreen() }
        composable(Screen.Labs.route) { LabsScreen() }
        composable(Screen.Settings.route) { SettingsScreen() }
    }
}
