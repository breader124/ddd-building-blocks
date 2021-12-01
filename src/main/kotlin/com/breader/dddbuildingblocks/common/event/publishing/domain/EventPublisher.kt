package com.breader.dddbuildingblocks.common.event.publishing.domain

import java.util.*

interface EventPublisher {
    fun publish(streamId: UUID, streamVersion: Int, events: List<DomainEvent>)
}
