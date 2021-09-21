package com.breader.dddbuildingblocks.organizer.model

import java.util.*

data class PlaySongRequest(
    val guitarId: UUID,
    val tuning: String,
    val pickupType: String,
    val pickupPos: String,
    val volumeLevel: Int,
    val toneLevel: Int
)