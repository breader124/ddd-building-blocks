package com.breader.dddbuildingblocks.guitar.application

import com.breader.dddbuildingblocks.guitar.model.Knob
import com.breader.dddbuildingblocks.guitar.model.Pickups
import com.breader.dddbuildingblocks.guitar.model.Tunings
import java.util.*

class ManufactureGuitarCommand(
    val commandId: UUID,
    val tunings: Tunings,
    val pickups: Pickups,
    val volumeKnob: Knob,
    val toneKnob: Knob
)
