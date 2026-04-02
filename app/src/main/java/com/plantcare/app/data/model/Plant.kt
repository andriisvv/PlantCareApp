package com.plantcare.app.data.model

data class Plant(
    val id: Int,
    var name: String,
    var species: String,
    var plantedDate: String,
    var photoResId: Int? = null,
    var condition: PlantCondition = PlantCondition.HEALTHY
)