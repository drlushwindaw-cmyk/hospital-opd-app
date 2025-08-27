package com.example.hospitalapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.hospitalapp.data.models.OutpatientVisit
import com.example.hospitalapp.viewmodel.HospitalViewModel

@Composable
fun OPDScreen(vm: HospitalViewModel) {
    val today = vm.todayIso()
    val visitsToday by vm.outpatientVisits.collectAsState()
    val todays = remember(today, visitsToday) { visitsToday.filter { it.date == today } }

    var patient by remember { mutableStateOf(TextFieldValue()) }
    var age by remember { mutableStateOf(TextFieldValue()) }
    var sex by remember { mutableStateOf(TextFieldValue()) }
    var phone by remember { mutableStateOf(TextFieldValue()) }
    var bp by remember { mutableStateOf(TextFieldValue()) }
    var pulse by remember { mutableStateOf(TextFieldValue()) }
    var temp by remember { mutableStateOf(TextFieldValue()) }

    var complaint by remember { mutableStateOf(TextFieldValue()) }
    var history by remember { mutableStateOf(TextFieldValue()) }
    var physical by remember { mutableStateOf(TextFieldValue()) }
    var investigations by remember { mutableStateOf(TextFieldValue()) }
    var diag by remember { mutableStateOf(TextFieldValue()) }
    var icd by remember { mutableStateOf(TextFieldValue()) }
    var treatment by remember { mutableStateOf(TextFieldValue()) }
    var meds by remember { mutableStateOf(TextFieldValue()) }
    var fuDate by remember { mutableStateOf(TextFieldValue()) }
    var fee by remember { mutableStateOf(TextFieldValue()) }
    var newVisit by remember { mutableStateOf(true) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("New OPD Visit – $today", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(value = patient, onValueChange = { patient = it }, label = { Text("Patient name") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = age, onValueChange = { age = it }, label = { Text("Age") }, modifier = Modifier.weight(0.6f))
            OutlinedTextField(value = sex, onValueChange = { sex = it }, label = { Text("Sex M/F/O") }, modifier = Modifier.weight(0.6f))
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = bp, onValueChange = { bp = it }, label = { Text("BP") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = pulse, onValueChange = { pulse = it }, label = { Text("Pulse") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = temp, onValueChange = { temp = it }, label = { Text("Temp") }, modifier = Modifier.weight(1f))
        }
        // Clinical documentation blocks
        OutlinedTextField(value = complaint, onValueChange = { complaint = it }, label = { Text("Complaint") }, modifier = Modifier.fillMaxWidth(), minLines = 2, maxLines = 4)
        OutlinedTextField(value = history, onValueChange = { history = it }, label = { Text("History") }, modifier = Modifier.fillMaxWidth(), minLines = 3, maxLines = 6)
        OutlinedTextField(value = physical, onValueChange = { physical = it }, label = { Text("Physical Examination") }, modifier = Modifier.fillMaxWidth(), minLines = 3, maxLines = 6)
        OutlinedTextField(value = investigations, onValueChange = { investigations = it }, label = { Text("Investigation(s)") }, modifier = Modifier.fillMaxWidth(), minLines = 2, maxLines = 5)
        OutlinedTextField(value = diag, onValueChange = { diag = it }, label = { Text("Diagnosis") }, modifier = Modifier.fillMaxWidth())
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(value = icd, onValueChange = { icd = it }, label = { Text("ICD-10 (optional)") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = treatment, onValueChange = { treatment = it }, label = { Text("Treatment") }, modifier = Modifier.weight(1f), minLines = 2, maxLines = 6)
        }
        OutlinedTextField(value = meds, onValueChange = { meds = it }, label = { Text("Medications (comma-separated)") }, modifier = Modifier.fillMaxWidth())
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(value = fuDate, onValueChange = { fuDate = it }, label = { Text("Follow-up date (yyyy-MM-dd)") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = fee, onValueChange = { fee = it }, label = { Text("Fee (optional)") }, modifier = Modifier.weight(1f))
        }
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Checkbox(checked = newVisit, onCheckedChange = { newVisit = it })
            Text("New visit", modifier = Modifier.padding(start = 8.dp))
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            if (patient.text.isNotBlank()) {
                vm.addOutpatientVisit(
                    OutpatientVisit(
                        date = today,
                        patientName = patient.text.trim(),
                        age = age.text.toIntOrNull(),
                        sex = sex.text.trim().ifBlank { null },
                        phone = phone.text.trim().ifBlank { null },
                        newVisit = newVisit,
                        complaint = complaint.text.trim(),
                        history = history.text.trim(),
                        physicalExam = physical.text.trim(),
                        investigations = investigations.text.trim(),
                        diagnosis = diag.text.trim(),
                        icdCode = icd.text.trim().ifBlank { null },
                        treatment = treatment.text.trim(),
                        medications = meds.text.trim(),
                        vitalsBp = bp.text.trim().ifBlank { null },
                        vitalsPulse = pulse.text.trim().ifBlank { null },
                        vitalsTemp = temp.text.trim().ifBlank { null },
                        followUpDate = fuDate.text.trim().ifBlank { null },
                        fee = fee.text.toDoubleOrNull()
                    )
                )
                // reset fields
                for (v in listOf(
                    , lambda: age, lambda: sex, lambda: phone,
                )): pass
            }
        }) { Text("Save visit") }

        Divider(Modifier.padding(vertical = 16.dp))
        Text("Today's Visits", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(todays) { v ->
                ElevatedCard {
                    Column(Modifier.padding(12.dp)) {
                        Text("${'$'}{v.patientName} • ${'$'}{if (v.newVisit) "New" else "Follow-up"}")
                        if (v.diagnosis.isNotBlank()) Text("Dx: ${'$'}{v.diagnosis}")
                        if (v.treatment.isNotBlank()) Text("Tx: ${'$'}{v.treatment}")
                        if (!v.followUpDate.isNullOrBlank()) Text("Follow-up: ${'$'}{v.followUpDate}")
                    }
                }
            }
        }
    }
}
