package com.example.hospitalapp.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.hospitalapp.nav.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(currentRoute: String) {
    val title = when (currentRoute) {
        Screen.OPD.route -> "Outpatient (OPD)"
        Screen.Patients.route -> "Patients"
        Screen.Appointments.route -> "Appointments"
        Screen.Pharmacy.route -> "Pharmacy"
        Screen.Reports.route -> "Reports"
        Screen.Doctors.route -> "Doctors"
        Screen.Labs.route -> "Labs"
        Screen.Settings.route -> "Settings"
        else -> "Hospital"
    }
    CenterAlignedTopAppBar(title = { Text(title) })
}
