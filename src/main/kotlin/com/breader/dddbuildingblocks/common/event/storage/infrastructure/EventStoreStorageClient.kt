package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import com.breader.dddbuildingblocks.common.event.storage.domain.PersistableEvent
import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.eventstore.dbclient.EventData
import com.eventstore.dbclient.EventStoreDBClient
import com.fasterxml.jackson.databind.ObjectMapper

class EventStoreStorageClient(
    private val dbClient: EventStoreDBClient,
    private val objectMapper: ObjectMapper = ObjectMapper()
) : StorageClient {

    override fun store(streamName: String, event: PersistableEvent) {
        val eventData = EventData(
            event.eventId,
            event.eventType,
            "json",
            objectMapper.writeValueAsBytes(event),
            ByteArray(0)
        )

        dbClient
            .runCatching {
                appendToStream(streamName, eventData).get()
            }
            .onFailure {
                throw it
            }
    }

}
