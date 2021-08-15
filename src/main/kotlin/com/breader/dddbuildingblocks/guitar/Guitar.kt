package com.breader.dddbuildingblocks.guitar

import java.util.*

class Guitar(
    val id: GuitarId,
    var tunings: Tunings,
    var pickups: Pickups,
    var volumeKnob: Knob,
    var toneKnob: Knob
) {

    fun playSong(partToPlay: PartToPlay): Result {
        TODO()
    }

    fun playWarmUp(partToPlay: PartToPlay): Result {
        TODO()
    }

}

class GuitarId {
    val id: UUID

    init {
        id = UUID.randomUUID()
    }
}
