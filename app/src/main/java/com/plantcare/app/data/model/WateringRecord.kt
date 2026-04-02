package com.plantcare.app.data.model

data class WateringRecord(
    val id: Int,
    val plantId: Int,
    val date: String,
    val notes: String = ""
)