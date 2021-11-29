package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import com.breader.dddbuildingblocks.common.event.storage.domain.PersistableEvent
import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.eventstore.dbclient.EventData
import com.eventstore.dbclient.EventStoreDBClient
import com.eventstore.dbclient.ReadStreamOptions
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream
import java.util.*

class EventStoreStorageClient(
    private val dbClient: EventStoreDBClient
) : StorageClient {

    override fun store(streamName: String, event: PersistableEvent) {
        dbClient
            .runCatching {
                val eventData = EventStoreData(event.eventId, event.aggregateId, event.eventData)
                val eventMetadata = EventStoreMetadata(event.version, event.happenedAt, event.correlationId, event.causationId)
                val readyToStoreEvent = EventData.builderAsJson(event.eventType, eventData)
                    .metadataAsJson(eventMetadata)
                    .build()
                appendToStream(streamName, readyToStoreEvent).get()
            }
            .onFailure {
                throw it
            }
    }

    override fun fetchEvents(streamName: String): List<PersistableEvent> {
        return dbClient.readStream(streamName, ReadStreamOptions.get()).get().events
            .map {
                val originalEvent = it.originalEvent
                val eventStoreData = byteArrayToEventStoreData<EventStoreData>(originalEvent.eventData)
                val eventStoreMetadata = byteArrayToEventStoreData<EventStoreMetadata>(originalEvent.userMetadata)
                PersistableEvent(
                    eventId = eventStoreData.eventId,
                    aggregateId = eventStoreData.aggregateID,
                    correlationId = eventStoreMetadata.correlationId,
                    causationId = eventStoreMetadata.causationId,
                    version = eventStoreMetadata.version,
                    happenedAt = eventStoreMetadata.happenedAt,
                    eventType = originalEvent.eventType,
                    eventData = eventStoreData.data
                )
            }
    }

    private fun <T> byteArrayToEventStoreData(byteArray: ByteArray): T {
        ObjectInputStream(ByteArrayInputStream(byteArray)).use {
            return it.readObject() as T
        }
    }
}

data class EventStoreData(
    val eventId: UUID,
    val aggregateID: UUID,
    val data: String
)

data class EventStoreMetadata(
    val version: Int,
    val happenedAt: Long,
    val correlationId: UUID,
    val causationId: UUID?
)
