package com.breader.dddbuildingblocks.organizer.model

import com.breader.dddbuildingblocks.guitar.application.PlayingSongCommand
import com.breader.dddbuildingblocks.guitar.model.*
import java.util.*

data class PlaySongRequest(
    val tuning: String,
    val pickupType: String,
    val pickupPos: String,
    val volumeLevel: Int,
    val toneLevel: Int
) {

    fun toCommand(guitarId: UUID): PlayingSongCommand {
        return PlayingSongCommand(
            UUID.randomUUID(),
            GuitarId(guitarId),
            Tuning.valueOf(tuning),
            Pickup.ofTypeAndPos(PickupType.valueOf(pickupType), PickupPosition.valueOf(pickupPos)),
            Knob.withLevel(volumeLevel),
            Knob.withLevel(toneLevel)
        )
    }

}
