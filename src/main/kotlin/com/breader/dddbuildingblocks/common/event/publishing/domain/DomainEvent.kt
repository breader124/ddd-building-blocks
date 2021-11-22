package com.breader.dddbuildingblocks.common.event.publishing.domain

import java.time.Instant
import java.util.*

abstract class DomainEvent(
    val eventId: UUID,       // to feed causationId forming a chain eventually
    val aggregateId: UUID,   // to know the source of an event
    val correlationId: UUID, // to easily fetch all events in transaction
    val causationId: UUID,   // to easily recreate chronological order
    val version: Int,        // to make optimistic locking possible
    val happenedAt: Instant  // to know when event happened
)
