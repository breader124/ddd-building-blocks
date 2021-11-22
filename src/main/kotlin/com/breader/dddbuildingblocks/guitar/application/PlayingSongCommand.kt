package com.breader.dddbuildingblocks.guitar.application

import com.breader.dddbuildingblocks.guitar.model.GuitarId
import com.breader.dddbuildingblocks.guitar.model.Pickup
import com.breader.dddbuildingblocks.guitar.model.Tuning
import java.util.*

data class PlayingSongCommand(
    val commandId: UUID,
    val guitarId: GuitarId,
    val tuning: Tuning,
    val pickup: Pickup,
    val volumeLevel: Int,
    val toneLevel: Int
)
