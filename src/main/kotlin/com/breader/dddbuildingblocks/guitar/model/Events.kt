package com.breader.dddbuildingblocks.guitar.model

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import java.time.Instant
import java.util.*

abstract class ParamSwitchedEvent<T>(
    aggregateId: UUID,
    happenedAt: Instant,
    version: Int,
    val from: T,
    val to: T
) : DomainEvent(aggregateId, happenedAt, version)

class Tuned(
    aggregateId: UUID,
    happenedAt: Instant,
    version: Int,
    from: Tuning,
    to: Tuning
) : ParamSwitchedEvent<Tuning>(aggregateId, happenedAt, version, from, to)

class VolKnobAdjusted(
    aggregateId: UUID,
    happenedAt: Instant,
    version: Int,
    from: Int,
    to: Int,
) : ParamSwitchedEvent<Int>(aggregateId, happenedAt, version, from, to)

class ToneKnobAdjusted(
    aggregateId: UUID,
    happenedAt: Instant,
    version: Int,
    from: Int,
    to: Int,
) : ParamSwitchedEvent<Int>(aggregateId, happenedAt, version, from, to)

class PickupSwitched(
    aggregateId: UUID,
    happenedAt: Instant,
    version: Int,
    from: Pickup,
    to: Pickup,
) : ParamSwitchedEvent<Pickup>(aggregateId, happenedAt, version, from, to)

class SongPlayed(
    aggregateId: UUID,
    happenedAt: Instant,
    version: Int
) : DomainEvent(aggregateId, happenedAt, version)
