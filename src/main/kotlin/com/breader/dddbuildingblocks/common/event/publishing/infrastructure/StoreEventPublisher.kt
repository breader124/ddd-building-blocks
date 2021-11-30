package com.breader.dddbuildingblocks.common.event.publishing.infrastructure

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import com.breader.dddbuildingblocks.common.event.publishing.domain.EventPublisher
import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.breader.dddbuildingblocks.common.event.storage.infrastructure.EventMapper
import java.util.*

class StoreEventPublisher(
    private val storageClient: StorageClient,
    private val eventMapper: EventMapper
) : EventPublisher {

    override fun publish(streamId: UUID, events: List<DomainEvent>) = storageClient.store(
        streamId,
        eventMapper.enrich(streamId, events)
    )

}
