package com.plantcare.app.data.model

data class Reminder(
    val id: Int,
    val plantId: Int,
    val type: ReminderType,
    val intervalDays: Int,
    var nextDate: String,
    var isActive: Boolean = true
)