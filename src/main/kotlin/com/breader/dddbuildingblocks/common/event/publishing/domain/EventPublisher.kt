package com.breader.dddbuildingblocks.common.event.publishing.domain

interface EventPublisher {
    fun publish(streamName: String, event: DomainEvent)

    fun publish(streamName: String, events: List<DomainEvent>) {
        events.forEach {
            publish(streamName, it)
        }
    }
}
