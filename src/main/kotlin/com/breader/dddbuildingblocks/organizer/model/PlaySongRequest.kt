package com.breader.dddbuildingblocks.organizer.model

import com.breader.dddbuildingblocks.guitar.application.PlayingSongCommand
import com.breader.dddbuildingblocks.guitar.model.*
import java.util.*

data class PlaySongRequest(
    val id: UUID,
    val tuning: String,
    val pickupType: String,
    val pickupPos: String,
    val volumeLevel: Int,
    val toneLevel: Int
) : Request<PlayingSongCommand> {

    override fun toCommand(): PlayingSongCommand {
        return PlayingSongCommand(
            GuitarId(id),
            Tuning.valueOf(tuning),
            Pickup.ofTypeAndPos(PickupType.valueOf(pickupType), PickupPosition.valueOf(pickupPos)),
            volumeLevel,
            toneLevel
        )
    }

}