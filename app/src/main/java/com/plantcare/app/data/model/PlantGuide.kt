package com.plantcare.app.data.model

data class PlantGuide(
    val id: Int,
    var title: String,
    var content: String,
    var wateringFrequency: String = "",
    var lightRequirement: String = "",
    var temperatureRange: String = "",
    var photoResId: Int? = null
)