package com.breader.dddbuildingblocks.common.event.publishing.domain

abstract class DomainEvent(
    val eventType: String,
    val version: Int
)
