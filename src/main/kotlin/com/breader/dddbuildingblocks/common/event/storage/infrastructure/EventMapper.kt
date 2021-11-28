package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import com.breader.dddbuildingblocks.common.event.storage.domain.PersistableEvent
import com.breader.dddbuildingblocks.guitar.model.ParamSwitchedEvent
import com.breader.dddbuildingblocks.guitar.model.VolKnobAdjusted
import com.fasterxml.jackson.databind.ObjectMapper

class EventMapper(
    private val objectMapper: ObjectMapper = ObjectMapper()
) {

    fun toPersistableEvent(domainEvent: DomainEvent): PersistableEvent = PersistableEvent(
        eventId = domainEvent.eventId,
        aggregateId = domainEvent.aggregateId,
        correlationId = domainEvent.correlationId,
        causationId = domainEvent.causationId,
        version = domainEvent.version,
        happenedAt = domainEvent.happenedAt.epochSecond,
        eventType = domainEvent.javaClass.typeName,
        eventData = when (domainEvent) {
            is ParamSwitchedEvent<*> -> mapDataToJson(domainEvent)
            else -> ""
        }
    )

    private fun <T> mapDataToJson(domainEvent: ParamSwitchedEvent<T>): String {
        val data = object {
            val from = domainEvent.from
            val to = domainEvent.to
        }
        return objectMapper.writeValueAsString(data)
    }

}
