package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import com.breader.dddbuildingblocks.common.event.storage.domain.PersistableEvent
import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.eventstore.dbclient.EventData
import com.eventstore.dbclient.EventStoreDBClient
import java.util.concurrent.CompletableFuture

class EventStoreStorageClient(private val dbClient: EventStoreDBClient) : StorageClient {

    override fun store(streamName: String, event: PersistableEvent): CompletableFuture<Unit> {
        val eventData = EventData
            .builderAsJson(event.eventType, event)
            .build()

        val writeResult = dbClient.appendToStream(streamName, eventData)

        return writeResult.thenApply {  }
    }

}
