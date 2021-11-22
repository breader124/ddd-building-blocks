package com.breader.dddbuildingblocks.common.event.storage.domain

import java.util.*

data class PersistableEvent(
    val eventId: UUID,
    val aggregateId: UUID,
    val userId: UUID,
    val correlationId: UUID,
    val causationId: UUID,
    val version: Int,
    val eventType: String,
    val eventData: String
)
