package com.breader.dddbuildingblocks.common.aggregate

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent

open class AggregateRoot(var aggregateVersion: Int) {
    private val domainEvents = mutableListOf<DomainEvent>()

    fun addEvent(domainEvent: DomainEvent) = domainEvents.add(domainEvent)

    fun getAndClearEvents(): List<DomainEvent> {
        val eventsCopy = domainEvents.toList()
        domainEvents.clear()
        return eventsCopy
    }
}
