package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StorageConfiguration {

    @Bean
    fun storageClient() = EventStoreStorageClient()

}
