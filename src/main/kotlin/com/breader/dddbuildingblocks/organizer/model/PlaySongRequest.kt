package com.breader.dddbuildingblocks.organizer.model

import com.breader.dddbuildingblocks.guitar.application.PlayingSongCommand
import java.util.*

data class PlaySongRequest(
    val guitarId: UUID,
    val tuning: String,
    val pickupType: String,
    val pickupPos: String,
    val volumeLevel: Int,
    val toneLevel: Int
) : Request<PlayingSongCommand> {

    override fun toCommand(): PlayingSongCommand {
        TODO("Not yet implemented")
    }

}