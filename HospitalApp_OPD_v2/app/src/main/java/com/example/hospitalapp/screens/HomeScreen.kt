package com.example.hospitalapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hospitalapp.nav.Screen

@Composable
fun HomeScreen(onNavigate: (Screen) -> Unit) {
    val items = listOf(
        Screen.OPD,
        Screen.Patients,
        Screen.Appointments,
        Screen.Reports,
        Screen.Pharmacy,
        Screen.Doctors,
        Screen.Labs,
        Screen.Settings
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items) { item ->
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.clickable { onNavigate(item) }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = androidx.compose.ui.Modifier
                            .padding(20.dp)
                    )
                }
            }
        }
    }
}
