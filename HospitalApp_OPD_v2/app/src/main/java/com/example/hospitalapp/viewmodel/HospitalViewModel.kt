package com.example.hospitalapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hospitalapp.data.InMemoryRepository
import com.example.hospitalapp.data.models.*
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HospitalViewModel : ViewModel() {
    private val repo = InMemoryRepository()

    val patients: StateFlow<List<Patient>> = repo.patients
    val appointments: StateFlow<List<Appointment>> = repo.appointments
    val medications: StateFlow<List<Medication>> = repo.medications
    val outpatientVisits: StateFlow<List<OutpatientVisit>> = repo.outpatientVisits

    fun addPatient(name: String, dob: String, phone: String, mrn: String?) {
        repo.addPatient(Patient(name = name, dob = dob, phone = phone, mrn = mrn))
    }

    fun addAppointment(patientName: String, doctor: String, date: String, reason: String) {
        repo.addAppointment(Appointment(patientName = patientName, doctor = doctor, date = date, reason = reason))
    }

    fun addMedication(name: String, dosage: String) {
        repo.addMedication(Medication(name = name, dosage = dosage))
    }

    fun addOutpatientVisit(v: OutpatientVisit) { repo.addOutpatientVisit(v) }
    fun visitsByDate(date: String) = repo.visitsByDate(date)
    fun visitsBetween(startDate: String, endDate: String) = repo.visitsBetween(startDate, endDate)
    fun dailySummary(date: String) = repo.dailySummary(date)

    fun todayIso(): String = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
}
