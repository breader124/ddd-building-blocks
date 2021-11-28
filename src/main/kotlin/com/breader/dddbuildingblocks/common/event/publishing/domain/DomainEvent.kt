package com.breader.dddbuildingblocks.common.event.publishing.domain

import java.time.Instant
import java.util.*

abstract class DomainEvent(
    val aggregateId: UUID,
    val happenedAt: Instant,
    val version: Int
) {
    val eventId: UUID = UUID.randomUUID()

    lateinit var correlationId: UUID
    var causationId: UUID? = null

    fun correlatesWith(correlationId: UUID) {
        this.correlationId = correlationId
    }

    fun causedBy(causationId: UUID?) {
        causationId?.let {
            this.causationId = it
        }
    }
}
