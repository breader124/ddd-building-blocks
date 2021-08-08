package com.breader.dddbuildingblocks.guitar

import java.util.*

class Guitar(
    private val id: GuitarId,
    private val supportedTunings: List<Tuning>,
    private val tuning: Tuning,
    private val pickups: Pickups,
    private val volumeKnob: Knob,
    private val toneKnob: Knob
) {

    fun playSong(partToPlay: PartToPlay): Result {
        TODO()
    }

    fun playWarmUp(partToPlay: PartToPlay): Result {
        TODO()
    }

}

class GuitarId(val id: UUID)
