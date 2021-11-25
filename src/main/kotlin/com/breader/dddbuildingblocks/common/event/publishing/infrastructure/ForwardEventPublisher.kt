package com.breader.dddbuildingblocks.common.event.publishing.infrastructure

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import com.breader.dddbuildingblocks.common.event.publishing.domain.EventPublisher
import org.springframework.context.ApplicationEventPublisher

class ForwardEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val eventPublisher: EventPublisher
) : EventPublisher {

    override fun publish(streamName: String, event: DomainEvent) {
        eventPublisher.publish(streamName, event)
        applicationEventPublisher.publishEvent(event)
    }

}
