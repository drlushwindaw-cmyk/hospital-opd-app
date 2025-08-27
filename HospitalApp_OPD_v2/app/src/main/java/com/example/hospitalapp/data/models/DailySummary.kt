package com.example.hospitalapp.data.models

data class DailySummary(
    val date: String,
    val totalVisits: Int,
    val newVisits: Int,
    val followUps: Int,
    val topDiagnoses: List<Pair<String, Int>>,
    val followUpsDue: Int,
    val revenue: Double?
)
