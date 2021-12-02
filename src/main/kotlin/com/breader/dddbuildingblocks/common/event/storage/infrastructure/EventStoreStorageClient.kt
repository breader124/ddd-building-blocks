package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import com.breader.dddbuildingblocks.common.event.storage.domain.PersistableEvent
import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.eventstore.dbclient.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.util.*

class EventStoreStorageClient(
    private val dbClient: EventStoreDBClient,
    private val objectMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())
) : StorageClient {

    override fun store(streamId: UUID, events: List<PersistableEvent>) {
        dbClient
            .runCatching {
                events.forEach { event ->
                    val eventMetadata = EventStoreMetadata(
                        event.eventId,
                        event.aggregateId,
                        event.version,
                        event.happenedAt,
                        event.correlationId,
                        event.causationId
                    )
                    val readyToStoreEvent = EventData.builderAsJson(event.eventType, event.eventData)
                        .metadataAsJson(eventMetadata)
                        .build()

                    val options = AppendToStreamOptions.get()
                    if (event.version == 0L) {
                        options.expectedRevision(ExpectedRevision.NO_STREAM)
                    } else {
                        options.expectedRevision(event.version - 1)
                    }
                    appendToStream(streamId.toString(), options, readyToStoreEvent).get()
                }
            }
            .onFailure {
                throw it
            }
    }

    override fun fetch(streamId: UUID): List<PersistableEvent> {
        return dbClient.readStream(streamId.toString()).get().events
            .map {
                val originalEvent = it.originalEvent
                val eventStoreData = objectMapper.readValue(originalEvent.eventData, String::class.java)
                val eventStoreMetadata = objectMapper.readValue(originalEvent.userMetadata, EventStoreMetadata::class.java)
                PersistableEvent(
                    eventId = eventStoreMetadata.eventId,
                    aggregateId = eventStoreMetadata.aggregateID,
                    correlationId = eventStoreMetadata.correlationId,
                    causationId = eventStoreMetadata.causationId,
                    version = eventStoreMetadata.version,
                    happenedAt = eventStoreMetadata.happenedAt,
                    eventType = originalEvent.eventType,
                    eventData = eventStoreData
                )
            }
    }

}

data class EventStoreMetadata(
    val eventId: UUID,
    val aggregateID: UUID,
    val version: Long,
    val happenedAt: Long,
    val correlationId: UUID,
    val causationId: UUID?
)
