package com.example.hospitalapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    Column(Modifier.padding(16.dp)) {
        Text("Settings", style = MaterialTheme.typography.titleLarge)
        Text("Add your hospital name, departments, and user roles here (future work).")
    }
}
