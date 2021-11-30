package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import com.breader.dddbuildingblocks.common.event.storage.domain.PersistableEvent
import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.eventstore.dbclient.EventData
import com.eventstore.dbclient.EventStoreDBClient
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

                    appendToStream(streamId.toString(), readyToStoreEvent).get()
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
                    eventId = eventStoreMetadata.eventId!!,
                    aggregateId = eventStoreMetadata.aggregateID!!,
                    correlationId = eventStoreMetadata.correlationId!!,
                    causationId = eventStoreMetadata.causationId,
                    version = eventStoreMetadata.version!!,
                    happenedAt = eventStoreMetadata.happenedAt!!,
                    eventType = originalEvent.eventType,
                    eventData = eventStoreData
                )
            }
    }

}

data class EventStoreMetadata(
    var eventId: UUID? = null,
    var aggregateID: UUID? = null,
    var version: Long? = null,
    var happenedAt: Long? = null,
    var correlationId: UUID? = null,
    var causationId: UUID? = null
)
