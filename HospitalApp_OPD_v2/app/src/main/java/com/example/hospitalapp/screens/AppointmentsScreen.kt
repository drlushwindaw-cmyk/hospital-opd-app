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
fun AppointmentsScreen(vm: HospitalViewModel) {
    val appts by vm.appointments.collectAsState()

    var patient by remember { mutableStateOf(TextFieldValue()) }
    var doctor by remember { mutableStateOf(TextFieldValue()) }
    var date by remember { mutableStateOf(TextFieldValue()) }
    var reason by remember { mutableStateOf(TextFieldValue()) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Book Appointment", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = patient, onValueChange = { patient = it }, label = { Text("Patient name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = doctor, onValueChange = { doctor = it }, label = { Text("Doctor") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date/Time") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = reason, onValueChange = { reason = it }, label = { Text("Reason") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            if (patient.text.isNotBlank() && doctor.text.isNotBlank()) {
                vm.addAppointment(patient.text.trim(), doctor.text.trim(), date.text.trim(), reason.text.trim())
                patient = TextFieldValue(""); doctor = TextFieldValue(""); date = TextFieldValue(""); reason = TextFieldValue("")
            }
        }) { Text("Add") }
        Divider(Modifier.padding(vertical = 16.dp))
        Text("Upcoming", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(appts) { a ->
                ElevatedCard {
                    Column(Modifier.padding(12.dp)) {
                        Text("${a.patientName} with ${a.doctor}", style = MaterialTheme.typography.titleMedium)
                        Text(a.date)
                        if (a.reason.isNotBlank()) Text("Reason: ${a.reason}")
                    }
                }
            }
        }
    }
}
