package com.breader.dddbuildingblocks.common.event.publishing.infrastructure

import com.breader.dddbuildingblocks.common.event.publishing.domain.EventPublisher
import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.breader.dddbuildingblocks.common.event.storage.infrastructure.EventMapper
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PublisherConfiguration {

    @Bean
    fun eventPublisher(
        appEventPublisher: ApplicationEventPublisher,
        storageClient: StorageClient,
        eventMapper: EventMapper
    ): EventPublisher = ForwardEventPublisher(appEventPublisher, StoreEventPublisher(storageClient, eventMapper))

}
