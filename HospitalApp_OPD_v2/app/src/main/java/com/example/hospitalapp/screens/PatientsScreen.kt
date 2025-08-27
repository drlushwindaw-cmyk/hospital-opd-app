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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientsScreen(vm: HospitalViewModel) {
    val patients by vm.patients.collectAsState()

    var name by remember { mutableStateOf(TextFieldValue()) }
    var dob by remember { mutableStateOf(TextFieldValue()) }
    var phone by remember { mutableStateOf(TextFieldValue()) }
    var mrn by remember { mutableStateOf(TextFieldValue()) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Add Patient", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Full name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = dob, onValueChange = { dob = it }, label = { Text("DOB (YYYY-MM-DD)") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = mrn, onValueChange = { mrn = it }, label = { Text("MRN (optional)") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            if (name.text.isNotBlank()) {
                vm.addPatient(name.text.trim(), dob.text.trim(), phone.text.trim(), mrn.text.ifBlank { null })
                name = TextFieldValue(""); dob = TextFieldValue(""); phone = TextFieldValue(""); mrn = TextFieldValue("")
            }
        }) { Text("Save") }
        Divider(Modifier.padding(vertical = 16.dp))
        Text("Patients", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(patients) { p ->
                ElevatedCard {
                    Column(Modifier.padding(12.dp)) {
                        Text(p.name, style = MaterialTheme.typography.titleMedium)
                        if (p.mrn != null) Text("MRN: ${p.mrn}")
                        Text("DOB: ${p.dob}")
                        Text("Phone: ${p.phone}")
                    }
                }
            }
        }
    }
}
