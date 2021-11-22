package com.breader.dddbuildingblocks.common.event.publishing.infrastructure

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import com.breader.dddbuildingblocks.common.event.storage.domain.PersistableEvent

class EventMapper {

    fun toPersistableEvent(domainEvent: DomainEvent) = PersistableEvent(
        eventId = domainEvent.eventId,
        aggregateId = domainEvent.aggregateId,
        correlationId = domainEvent.correlationId,
        causationId = domainEvent.causationId,
        version = domainEvent.version,
        happenedAt = domainEvent.happenedAt,
        eventType = domainEvent.javaClass.typeName,
        eventData = ""
    )

    // more functions to add here to cover more specific types of events whenever they appear

}
