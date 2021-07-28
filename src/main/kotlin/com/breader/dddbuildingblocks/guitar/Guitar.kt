package com.breader.dddbuildingblocks.guitar

import java.util.*

class Guitar(
    private val id: GuitarId,
    private val pickups: Pickups,
    private val supportedTunings: List<Tuning>,
    private val tuning: Tuning,
    private val volumeKnob: Knob,
    private val toneKnob: Knob
) {

    fun playRhythmPart(songPart: SongPart): Result {
        TODO()
    }

    fun playSolo(songPart: SongPart): Result {
        TODO()
    }

}

class GuitarId(val id: UUID)
