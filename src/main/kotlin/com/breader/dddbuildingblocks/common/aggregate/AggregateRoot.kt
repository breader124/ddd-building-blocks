package com.breader.dddbuildingblocks.common.aggregate

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent

open class AggregateRoot(val version: Int) {
    val domainEvents = mutableListOf<DomainEvent>()

    fun publishEvent(event: DomainEvent) {
        domainEvents.add(event)
    }

    fun publishEvents(events: List<DomainEvent>) {
        domainEvents.addAll(events)
    }
}
