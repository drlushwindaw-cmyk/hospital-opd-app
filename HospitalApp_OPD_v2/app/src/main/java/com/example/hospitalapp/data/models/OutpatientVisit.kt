package com.example.hospitalapp.data.models

data class OutpatientVisit(
    val id: String = java.util.UUID.randomUUID().toString(),
    val date: String, // ISO yyyy-MM-dd
    val time: String? = null, // HH:mm optional
    val patientName: String,
    val age: Int? = null,
    val sex: String? = null, // M/F/O
    val phone: String? = null,
    val newVisit: Boolean = true,
    // Clinical Documentation
    val complaint: String = "",         // CC
    val history: String = "",           // History of present illness, PMH, allergies
    val physicalExam: String = "",      // PE
    val investigations: String = "",    // labs/imaging ordered or results
    val diagnosis: String = "",         // Working dx (free text)
    val icdCode: String? = null,        // optional ICD code
    val treatment: String = "",         // plan summary
    val medications: String = "",       // comma-separated for MVP
    // Vitals
    val vitalsBp: String? = null,
    val vitalsPulse: String? = null,
    val vitalsTemp: String? = null,
    // Follow-up
    val followUpDate: String? = null,   // ISO yyyy-MM-dd
    val fee: Double? = null,
    val referredTo: String? = null,
    val notes: String = ""
)
