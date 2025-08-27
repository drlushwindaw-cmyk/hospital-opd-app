package com.example.hospitalapp.data.models

data class Medication(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val dosage: String
)
