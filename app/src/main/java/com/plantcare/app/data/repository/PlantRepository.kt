package com.plantcare.app.data.repository

import com.plantcare.app.R
import com.plantcare.app.data.model.*

object PlantRepository {

    private var nextPlantId = 7
    private var nextWateringId = 20
    private var nextCareId = 20
    private var nextGuideId = 5
    private var nextReminderId = 10

    // Mock Auth
    const val MOCK_LOGIN = "user"
    const val MOCK_PASSWORD = "1234"

    // ===== PLANTS =====
    val plants = arrayListOf(
        Plant(1, "Фікус Бенджаміна", "Ficus benjamina", "2024-03-15", R.drawable.plant_sample_1, PlantCondition.HEALTHY),
        Plant(2, "Кактус Ехінопсис", "Echinopsis pachanoi", "2024-06-20", R.drawable.plant_sample_2, PlantCondition.HEALTHY),
        Plant(3, "Орхідея Фаленопсис", "Phalaenopsis amabilis", "2023-11-10", R.drawable.plant_sample_3, PlantCondition.NEEDS_ATTENTION),
        Plant(4, "Монстера Делікатесна", "Monstera deliciosa", "2025-01-05", R.drawable.plant_sample_4, PlantCondition.SICK),
        Plant(5, "Потос Золотистий", "Epipremnum aureum", "2023-05-22", R.drawable.plant_sample_5, PlantCondition.HEALTHY),
        Plant(6, "Алое вера", "Aloe vera", "2024-09-10", R.drawable.plant_sample_6, PlantCondition.NEEDS_ATTENTION)
    )

    // ===== WATERING RECORDS =====
    val wateringRecords = arrayListOf(
        WateringRecord(1, 1, "2026-03-31", "Звичайний полив"),
        WateringRecord(2, 1, "2026-03-28", ""),
        WateringRecord(3, 2, "2026-03-27", ""),
        WateringRecord(4, 3, "2026-03-24", "Тепла вода"),
        WateringRecord(5, 4, "2026-04-01", ""),
        WateringRecord(6, 5, "2026-03-30", ""),
        WateringRecord(7, 5, "2026-03-26", ""),
        WateringRecord(8, 6, "2026-03-20", "Потрібно більше")
    )

    // ===== CARE RECORDS =====
    val careRecords = arrayListOf(
        CareRecord(1, 1, CareType.TRANSPLANT, "2025-09-10", null, "Більший горщик"),
        CareRecord(2, 1, CareType.MEASURE_HEIGHT, "2026-03-20", "45", ""),
        CareRecord(3, 3, CareType.FERTILIZE, "2026-03-01", null, "Добриво для орхідей"),
        CareRecord(4, 4, CareType.MEASURE_HEIGHT, "2026-03-25", "78", "Великі листки")
    )

    // ===== GUIDES =====
    val guides = arrayListOf(
        PlantGuide(1, "Догляд за фікусом", "Фікус Бенджаміна — популярна кімнатна рослина. Любить яскраве розсіяне світло.", "Кожні 3-4 дні", "Яскраве розсіяне", "18-25°C", R.drawable.plant_sample_1),
        PlantGuide(2, "Догляд за монстерою", "Монстера — тропічна рослина з великим листям. Потребує високої вологості.", "Кожні 5-7 днів", "Напівтінь", "20-28°C", R.drawable.plant_sample_4),
        PlantGuide(3, "Догляд за кактусами", "Кактуси невибагливі. Головне — не переливати та забезпечити багато сонця.", "Кожні 14-21 день", "Пряме сонце", "15-35°C", R.drawable.plant_sample_2),
        PlantGuide(4, "Догляд за орхідеєю", "Поливати методом занурення. Спеціальний субстрат з кори.", "Кожні 7 днів", "Розсіяне світло", "18-25°C", R.drawable.plant_sample_3)
    )

    // ===== REMINDERS =====
    val reminders = arrayListOf(
        Reminder(1, 1, ReminderType.WATERING, 3, "2026-04-04", true),
        Reminder(2, 2, ReminderType.WATERING, 14, "2026-04-10", true),
        Reminder(3, 3, ReminderType.WATERING, 7, "2026-04-01", true),
        Reminder(4, 4, ReminderType.WATERING, 5, "2026-04-06", true)
    )

    // ===== CRUD: Plants =====
    fun addPlant(plant: Plant): Plant {
        val newPlant = plant.copy(id = nextPlantId++)
        plants.add(newPlant)
        return newPlant
    }

    fun getPlantById(id: Int): Plant? = plants.find { it.id == id }

    fun deletePlant(id: Int) {
        plants.removeAll { it.id == id }
        wateringRecords.removeAll { it.plantId == id }
        careRecords.removeAll { it.plantId == id }
        reminders.removeAll { it.plantId == id }
    }

    // ===== CRUD: Watering =====
    fun addWatering(plantId: Int, date: String, notes: String = ""): WateringRecord {
        val record = WateringRecord(nextWateringId++, plantId, date, notes)
        wateringRecords.add(0, record)
        return record
    }

    fun getWateringsForPlant(plantId: Int): List<WateringRecord> =
        wateringRecords.filter { it.plantId == plantId }.sortedByDescending { it.date }

    fun getLastWatering(plantId: Int): WateringRecord? =
        getWateringsForPlant(plantId).firstOrNull()

    // ===== CRUD: Care =====
    fun addCareRecord(plantId: Int, type: CareType, date: String, value: String? = null, notes: String = ""): CareRecord {
        val record = CareRecord(nextCareId++, plantId, type, date, value, notes)
        careRecords.add(0, record)
        return record
    }

    fun getCareRecordsForPlant(plantId: Int): List<CareRecord> =
        careRecords.filter { it.plantId == plantId }.sortedByDescending { it.date }

    // ===== CRUD: Guides =====
    fun addGuide(guide: PlantGuide): PlantGuide {
        val newGuide = guide.copy(id = nextGuideId++)
        guides.add(newGuide)
        return newGuide
    }

    fun getGuideById(id: Int): PlantGuide? = guides.find { it.id == id }

    fun updateGuide(guide: PlantGuide) {
        val index = guides.indexOfFirst { it.id == guide.id }
        if (index != -1) guides[index] = guide
    }

    fun deleteGuide(id: Int) {
        guides.removeAll { it.id == id }
    }

    // ===== Statistics =====
    fun getTotalWaterings(): Int = wateringRecords.size
    fun getTotalPlants(): Int = plants.size
    fun getTotalGuides(): Int = guides.size
}