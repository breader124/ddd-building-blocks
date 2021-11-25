package com.breader.dddbuildingblocks.common.event.publishing.domain

interface EventPublisher {
    fun publish(streamName: String, event: DomainEvent)
}
