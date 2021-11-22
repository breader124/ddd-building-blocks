package com.breader.dddbuildingblocks.common.event.publishing.domain

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent

interface EventPublisher {
    fun publish(event: DomainEvent)
}
