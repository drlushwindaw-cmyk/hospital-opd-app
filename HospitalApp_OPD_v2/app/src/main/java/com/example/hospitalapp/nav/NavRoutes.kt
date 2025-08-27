package com.example.hospitalapp.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector?) {
    data object Home : Screen("home", "Home", Icons.Filled.Home)
    data object OPD : Screen("opd", "OPD", Icons.Filled.MedicalServices)
    data object Patients : Screen("patients", "Patients", Icons.Filled.People)
    data object Appointments : Screen("appointments", "Appointments", Icons.Filled.Event)
    data object Pharmacy : Screen("pharmacy", "Pharmacy", Icons.Filled.LocalPharmacy)
    data object Reports : Screen("reports", "Reports", Icons.Filled.ReceiptLong)
    data object Doctors : Screen("doctors", "Doctors", null)
    data object Labs : Screen("labs", "Labs", null)
    data object Settings : Screen("settings", "Settings", null)
}
