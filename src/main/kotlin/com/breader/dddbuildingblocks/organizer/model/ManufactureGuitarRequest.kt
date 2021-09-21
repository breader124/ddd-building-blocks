package com.breader.dddbuildingblocks.organizer.model

data class ManufactureGuitarRequest(
    val availableTunings: List<String>,
    val chosenTuning: String,
    val availablePickups: List<String>,
    val chosenPickup: String,
    val volumeKnobLevel: Int,
    val toneKnobLevel: Int
)