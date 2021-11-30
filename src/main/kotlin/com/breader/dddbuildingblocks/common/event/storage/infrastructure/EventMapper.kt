package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import com.breader.dddbuildingblocks.common.event.storage.domain.PersistableEvent
import com.breader.dddbuildingblocks.guitar.model.*
import com.eventstore.dbclient.EventStoreDBClient
import com.eventstore.dbclient.ReadResult
import com.eventstore.dbclient.ResolvedEvent
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.lang.Exception
import java.time.Instant
import java.util.*
import java.util.concurrent.CancellationException
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class EventMapper(
    private val eventStoreDBClient: EventStoreDBClient,
    private val objectMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())
) {

    companion object {
        val eventClassMap: Map<String, String?> = mapOf(
            "guitar_manufactured_v1" to GuitarManufactured::class.qualifiedName,
            "guitar_tuned_v1" to Tuned::class.qualifiedName,
            "vol_knob_adjusted_v1" to VolKnobAdjusted::class.qualifiedName,
            "tone_knob_adjusted_v1" to ToneKnobAdjusted::class.qualifiedName,
            "pickup_switched_v1" to PickupSwitched::class.qualifiedName,
            "song_played_v1" to SongPlayed::class.qualifiedName
        )
    }

    fun enrich(streamId: UUID, domainEvents: List<DomainEvent>): List<PersistableEvent> {
        val correlationId = UUID.randomUUID()
        val eventIds = (0..domainEvents.size).map { UUID.randomUUID() }
        var currentEventRevision = readLastEventRevision(streamId)
        return domainEvents.mapIndexed { index, domainEvent ->
            PersistableEvent(
                eventId = eventIds[index],
                aggregateId = streamId,
                correlationId = correlationId,
                causationId = eventIds.getOrNull(index - 1),
                version = ++currentEventRevision,
                happenedAt = Instant.now().epochSecond,
                eventType = domainEvent.eventType,
                eventData = objectMapper.writeValueAsString(domainEvent)
            )
        }
    }

    private fun readLastEventRevision(streamId: UUID): Long {
        return try {
            eventStoreDBClient.readStream(streamId.toString(), 1).get()
                .events[0]
                .originalEvent
                .streamRevision
                .valueUnsigned
        } catch (exc: Exception) {
            0
        }
    }

    fun extract(persistableEvent: PersistableEvent): DomainEvent {
        val data = persistableEvent.eventData
        val domainEventClassName = eventClassMap[persistableEvent.eventType] ?: throw RuntimeException("Fatal deserialization error")
        return objectMapper.readValue(data, Class.forName(domainEventClassName)) as DomainEvent
    }

}
