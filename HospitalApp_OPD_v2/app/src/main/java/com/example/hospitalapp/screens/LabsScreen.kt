package com.example.hospitalapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LabsScreen() {
    Column(Modifier.padding(16.dp)) {
        Text("Laboratory", style = MaterialTheme.typography.titleLarge)
        Text("Placeholder for ordering tests and viewing results.")
    }
}
