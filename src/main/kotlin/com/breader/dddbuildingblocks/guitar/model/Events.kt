package com.breader.dddbuildingblocks.guitar.model

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import java.time.Instant
import java.util.*

class Tuned(
    aggregateId: UUID,
    happenedAt: Instant,
    val from: Tuning,
    val to: Tuning,
) : DomainEvent(aggregateId, happenedAt)

class VolKnobAdjusted(
    aggregateId: UUID,
    happenedAt: Instant,
    val from: Int,
    val to: Int,
) : DomainEvent(aggregateId, happenedAt)

class ToneKnobAdjusted(
    aggregateId: UUID,
    happenedAt: Instant,
    val from: Int,
    val to: Int,
) : DomainEvent(aggregateId, happenedAt)

class PickupSwitched(
    aggregateId: UUID,
    happenedAt: Instant,
    val from: Pickup,
    val to: Pickup,
) : DomainEvent(aggregateId, happenedAt)
