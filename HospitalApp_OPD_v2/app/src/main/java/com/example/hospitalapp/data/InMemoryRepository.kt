package com.example.hospitalapp.data

import com.example.hospitalapp.data.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InMemoryRepository {
    private val _patients = MutableStateFlow<List<Patient>>(emptyList())
    val patients: StateFlow<List<Patient>> = _patients

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments

    private val _medications = MutableStateFlow<List<Medication>>(emptyList())
    val medications: StateFlow<List<Medication>> = _medications

    private val _outpatientVisits = MutableStateFlow<List<OutpatientVisit>>(emptyList())
    val outpatientVisits: StateFlow<List<OutpatientVisit>> = _outpatientVisits

    fun addPatient(patient: Patient) { _patients.value = _patients.value + patient }
    fun addAppointment(appt: Appointment) { _appointments.value = _appointments.value + appt }
    fun addMedication(med: Medication) { _medications.value = _medications.value + med }

    fun addOutpatientVisit(visit: OutpatientVisit) {
        _outpatientVisits.value = _outpatientVisits.value + visit
    }

    fun visitsByDate(date: String): List<OutpatientVisit> =
        _outpatientVisits.value.filter { it.date == date }

    fun visitsBetween(startDate: String, endDate: String): List<OutpatientVisit> =
        _outpatientVisits.value.filter { it.date >= startDate && it.date <= endDate }

    fun dailySummary(date: String): DailySummary {
        val list = visitsByDate(date)
        val total = list.size
        val newV = list.count { it.newVisit }
        val fu = total - newV
        val diagCounts = list.groupBy { it.diagnosis.ifBlank { "Unspecified" } }
            .mapValues { it.value.size }
            .toList().sortedByDescending { it.second }.take(5)
        val fuDue = list.count { it.followUpDate == date }
        val revenue = list.mapNotNull { it.fee }.sumOrNull()
        return DailySummary(date, total, newV, fu, diagCounts, fuDue, revenue)
    }

    private fun List<Double>.sumOrNull(): Double? = if (isEmpty()) null else this.sum()
}
