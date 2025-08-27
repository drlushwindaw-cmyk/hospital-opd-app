package com.example.hospitalapp.data.models

data class Appointment(
    val id: String = java.util.UUID.randomUUID().toString(),
    val patientName: String,
    val doctor: String,
    val date: String,
    val reason: String
)
