package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.eventstore.dbclient.EventStoreDBClient
import com.eventstore.dbclient.EventStoreDBConnectionString
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StorageConfiguration(@Value("\${event.storage.url}") val eventStoreUrl: String) {

    @Bean
    fun eventStoreDBClient(): EventStoreDBClient {
        val eventStoreSettings = EventStoreDBConnectionString.parseOrThrow(eventStoreUrl)
        return EventStoreDBClient.create(eventStoreSettings)
    }

    @Bean
    fun eventMapper(): EventMapper = EventMapper()

    @Bean
    fun storageClient(eventStoreDBClient: EventStoreDBClient): StorageClient {
        return EventStoreStorageClient(eventStoreDBClient)
    }

}
