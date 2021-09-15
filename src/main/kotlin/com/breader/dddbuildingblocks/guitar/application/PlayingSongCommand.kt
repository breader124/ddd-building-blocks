package com.breader.dddbuildingblocks.guitar.application

import com.breader.dddbuildingblocks.guitar.model.GuitarId
import com.breader.dddbuildingblocks.guitar.model.Pickup
import com.breader.dddbuildingblocks.guitar.model.Tuning

data class PlayingSongCommand(
    val guitarId: GuitarId,
    val tuning: Tuning,
    val pickup: Pickup,
    val volumeLevel: Int,
    val toneLevel: Int
)
