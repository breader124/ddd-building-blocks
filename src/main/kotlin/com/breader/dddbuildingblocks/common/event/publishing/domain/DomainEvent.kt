package com.breader.dddbuildingblocks.common.event.publishing.domain

import java.time.Instant
import java.util.*

abstract class DomainEvent(
    val aggregateId: UUID,
    val happenedAt: Instant
) {
    val eventId: UUID = UUID.randomUUID()
}
