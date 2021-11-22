package com.breader.dddbuildingblocks.common.event.publishing.infrastructure

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import com.breader.dddbuildingblocks.common.event.publishing.domain.EventPublisher
import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient

class StoreEventPublisher(val storageClient: StorageClient) : EventPublisher {

    override fun publish(event: DomainEvent) {

    }

}
