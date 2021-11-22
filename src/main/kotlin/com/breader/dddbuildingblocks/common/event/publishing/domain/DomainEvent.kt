package com.breader.dddbuildingblocks.common.event.publishing.domain

import java.time.Instant
import java.util.*

abstract class DomainEvent(
    val eventId: UUID,
    val aggregateId: UUID,
    val happenedAt: Instant
)
