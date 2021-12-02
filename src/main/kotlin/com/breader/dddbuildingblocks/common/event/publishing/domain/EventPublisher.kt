package com.breader.dddbuildingblocks.common.event.publishing.domain

import java.util.*

interface EventPublisher {
    fun publish(streamId: UUID, events: List<DomainEvent>)
}
