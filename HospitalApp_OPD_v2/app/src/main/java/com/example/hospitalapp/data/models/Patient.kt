package com.example.hospitalapp.data.models

data class Patient(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val dob: String,
    val phone: String,
    val mrn: String? = null
)
