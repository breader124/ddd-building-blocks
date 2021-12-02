package com.breader.dddbuildingblocks.common.event.publishing.infrastructure

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import com.breader.dddbuildingblocks.common.event.publishing.domain.EventPublisher
import org.springframework.context.ApplicationEventPublisher
import java.util.*

class ForwardEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val eventPublisher: EventPublisher
) : EventPublisher {

    override fun publish(streamId: UUID, events: List<DomainEvent>) {
        eventPublisher.publish(streamId, events)
        events.forEach { applicationEventPublisher.publishEvent(it) }
    }

}
