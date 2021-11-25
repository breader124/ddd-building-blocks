package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import com.breader.dddbuildingblocks.common.event.storage.domain.PersistableEvent
import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.eventstore.dbclient.EventData
import com.eventstore.dbclient.EventStoreDBClient

class EventStoreStorageClient(private val dbClient: EventStoreDBClient) : StorageClient {

    override fun store(streamName: String, event: PersistableEvent) {
        val eventData = EventData
            .builderAsJson(event.eventType, event)
            .build()

        dbClient
            .runCatching {
                appendToStream(streamName, eventData).get()
            }
            .onFailure {
                throw it
            }
    }

}
