package com.breader.dddbuildingblocks.guitar.application

import com.breader.dddbuildingblocks.guitar.model.Pickups
import com.breader.dddbuildingblocks.guitar.model.Tunings

class ManufactureGuitarCommand(
    val tunings: Tunings,
    val pickups: Pickups,
    val volumeKnobLevel: Int,
    val toneKnobLevel: Int
)
