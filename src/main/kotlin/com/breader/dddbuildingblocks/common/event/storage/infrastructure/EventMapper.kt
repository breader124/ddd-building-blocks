package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import com.breader.dddbuildingblocks.common.event.storage.domain.PersistableEvent
import com.breader.dddbuildingblocks.guitar.model.*
import com.eventstore.dbclient.ResolvedEvent
import com.fasterxml.jackson.databind.ObjectMapper
import java.time.Instant
import kotlin.reflect.full.primaryConstructor

class EventMapper(
    private val objectMapper: ObjectMapper = ObjectMapper()
) {

    fun toPersistableEvent(domainEvent: DomainEvent): PersistableEvent = PersistableEvent(
        eventId = domainEvent.eventId,
        aggregateId = domainEvent.aggregateId,
        correlationId = domainEvent.correlationId,
        causationId = domainEvent.causationId,
        version = domainEvent.version,
        happenedAt = domainEvent.happenedAt.epochSecond,
        eventType = domainEvent.javaClass.typeName,
        eventData = when (domainEvent) {
            is ParamSwitchedEvent<*> -> mapDataToJson(domainEvent)
            else -> ""
        }
    )

    private fun <T> mapDataToJson(domainEvent: ParamSwitchedEvent<T>): String {
        val data = ParamSwitchedEventData(domainEvent.from, domainEvent.to)
        return objectMapper.writeValueAsString(data)
    }

    fun toDomainEvent(persistableEvent: PersistableEvent): DomainEvent {
        val eventDataAsJson = persistableEvent.eventData
        val eventData = objectMapper.readValue(eventDataAsJson, ParamSwitchedEventData::class.java)

        val eventId = persistableEvent.eventId
        val happenedAt = Instant.ofEpochSecond(persistableEvent.happenedAt)
        val version = persistableEvent.version

        val domainEventClass = Class.forName(persistableEvent.eventType).kotlin
        return when (domainEventClass) {
            Tuned::class -> Tuned(eventId, happenedAt, version, eventData.from as Tuning, eventData.to as Tuning)
            VolKnobAdjusted::class -> VolKnobAdjusted(eventId, happenedAt, version, eventData.from as Int, eventData.to as Int)
            ToneKnobAdjusted::class -> ToneKnobAdjusted(eventId, happenedAt, version, eventData.from as Int, eventData.to as Int)
            PickupSwitched::class -> PickupSwitched(eventId, happenedAt, version, eventData.from as Pickup, eventData.to as Pickup)
            SongPlayed::class -> SongPlayed(eventId, happenedAt, version)
            else -> throw IllegalArgumentException("Given class ${domainEventClass.qualifiedName} cannot be instantiated")
        }
    }

}

data class ParamSwitchedEventData<T>(
    val from: T,
    val to: T
)
