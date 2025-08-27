package com.example.hospitalapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.hospitalapp.utils.CSVExporter
import com.example.hospitalapp.viewmodel.HospitalViewModel

@Composable
fun ReportsScreen(vm: HospitalViewModel) {
    val ctx = LocalContext.current
    var start by remember { mutableStateOf(TextFieldValue(vm.todayIso())) }
    var end by remember { mutableStateOf(TextFieldValue(vm.todayIso())) }

    val list = remember { mutableStateListOf<com.example.hospitalapp.data.models.OutpatientVisit>() }
    val summary by remember {
        derivedStateOf {
            if (start.text.isNotBlank()) vm.dailySummary(start.text) else null
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("OPD Reports", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(value = start, onValueChange = { start = it }, label = { Text("Start date (yyyy-MM-dd)") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = end, onValueChange = { end = it }, label = { Text("End date (yyyy-MM-dd)") }, modifier = Modifier.weight(1f))
            Button(onClick = {
                list.clear()
                list.addAll(vm.visitsBetween(start.text.trim(), end.text.trim()))
            }) { Text("Load") }
        }

        Spacer(Modifier.height(12.dp))
        if (summary != null) {
            ElevatedCard {
                Column(Modifier.padding(12.dp)) {
                    Text("Daily Summary: ${summary!!.date}", style = MaterialTheme.typography.titleMedium)
                    Text("Total visits: ${summary!!.totalVisits}")
                    Text("New: ${summary!!.newVisits} • Follow-up: ${summary!!.followUps}")
                    Text("Top diagnoses:")
                    summary!!.topDiagnoses.forEach { (dx, count) -> Text("- $dx: $count") }
                    Text("Follow-ups due today: ${summary!!.followUpsDue}")
                    if (summary!!.revenue != null) Text("Revenue (fees): ${summary!!.revenue}")
                }
            }
        }

        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(enabled = list.isNotEmpty(), onClick = {
                val csv = CSVExporter.visitsToCsv(list)
                CSVExporter.shareCsv(ctx, csv, "opd_${start.text}_${end.text}.csv")
            }) { Text("Export CSV") }
        }

        Divider(Modifier.padding(vertical = 12.dp))
        Text("Visits in Range: ${list.size}", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(list) { v ->
                ElevatedCard {
                    Column(Modifier.padding(12.dp)) {
                        Text("${v.date} • ${v.patientName}")
                        if (v.diagnosis.isNotBlank()) Text("Dx: ${v.diagnosis}")
                        if (v.treatment.isNotBlank()) Text("Tx: ${v.treatment}")
                        if (!v.followUpDate.isNullOrBlank()) Text("Follow-up: ${v.followUpDate}")
                    }
                }
            }
        }
    }
}
