package com.breader.dddbuildingblocks.common.event.publishing.infrastructure

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import com.breader.dddbuildingblocks.common.event.publishing.domain.EventPublisher
import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient

class StoreEventPublisher(
    private val storageClient: StorageClient,
    private val eventMapper: EventMapper
) : EventPublisher {

    override fun publish(event: DomainEvent) = storageClient.store(eventMapper.toPersistableEvent(event))

}
