package com.example.hospitalapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.hospitalapp.viewmodel.HospitalViewModel

@Composable
fun PharmacyScreen(vm: HospitalViewModel) {
    val meds by vm.medications.collectAsState()
    var name by remember { mutableStateOf(TextFieldValue()) }
    var dose by remember { mutableStateOf(TextFieldValue()) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Add Medication (demo)", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = dose, onValueChange = { dose = it }, label = { Text("Dosage") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            if (name.text.isNotBlank()) {
                vm.addMedication(name.text.trim(), dose.text.trim())
                name = TextFieldValue(""); dose = TextFieldValue("")
            }
        }) { Text("Save") }
        Divider(Modifier.padding(vertical = 16.dp))
        Text("Inventory (demo)", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(meds) { m ->
                ElevatedCard {
                    Column(Modifier.padding(12.dp)) {
                        Text(m.name, style = MaterialTheme.typography.titleMedium)
                        if (m.dosage.isNotBlank()) Text("Dosage: ${m.dosage}")
                    }
                }
            }
        }
    }
}
