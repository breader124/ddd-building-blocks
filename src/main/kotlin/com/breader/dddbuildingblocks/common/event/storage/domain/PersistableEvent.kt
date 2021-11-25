package com.breader.dddbuildingblocks.common.event.storage.domain

import java.util.*

data class PersistableEvent(
    val eventId: UUID,
    val aggregateId: UUID,
//    val userId: UUID,         // commented out as on the current stage, security and user management is not enabled
    val correlationId: UUID,
    val causationId: UUID,
    val version: Int,
    val happenedAt: Long,
    val eventType: String,
    val eventData: String
)
