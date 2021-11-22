package com.breader.dddbuildingblocks.common.event.publishing.domain

interface EventPublisher {
    fun publish(event: DomainEvent)
}
