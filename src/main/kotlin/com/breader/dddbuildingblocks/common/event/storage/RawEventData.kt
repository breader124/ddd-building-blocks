package com.breader.dddbuildingblocks.common.event.storage

import java.util.*

data class RawEventData(
    val eventId: UUID,
    val eventType: String,
    val isJson: Boolean,
    val data: ByteArray,
    val metadata: ByteArray
)
