package com.plantcare.app.data.model

data class CareRecord(
    val id: Int,
    val plantId: Int,
    val type: CareType,
    val date: String,
    val value: String? = null,
    val notes: String = ""
)