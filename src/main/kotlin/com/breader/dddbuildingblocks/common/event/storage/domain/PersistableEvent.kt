package com.breader.dddbuildingblocks.common.event.storage.domain

import java.util.*

data class PersistableEvent(
    val eventId: UUID,
    val aggregateId: UUID,
//    val userId: UUID,             // there's no security context at the moment
    val correlationId: UUID,
    val causationId: UUID?,
    val version: Long,
    val happenedAt: Long,
    val eventType: String,
    var eventData: String
)
