package com.breader.dddbuildingblocks.common.event.publishing.domain

import java.util.*

interface EventPublisher {
    fun publish(streamName: String, event: DomainEvent)

    fun publish(streamName: String, events: List<DomainEvent>) {
        val correlationId = UUID.randomUUID()

        events.forEachIndexed { index, event ->
            event.correlatesWith(correlationId)
            event.causedBy(events.getOrNull(index - 1)?.eventId)
            publish(streamName, event)
        }
    }
}
