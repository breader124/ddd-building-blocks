package com.breader.dddbuildingblocks.common.event.storage.domain

import java.util.*

data class RawEventData(
    val eventId: UUID,
    val eventType: String,
    val isJson: Boolean,
    val data: ByteArray,
    val metadata: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RawEventData

        if (eventId != other.eventId) return false
        if (eventType != other.eventType) return false
        if (isJson != other.isJson) return false
        if (!data.contentEquals(other.data)) return false
        if (!metadata.contentEquals(other.metadata)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = eventId.hashCode()
        result = 31 * result + eventType.hashCode()
        result = 31 * result + isJson.hashCode()
        result = 31 * result + data.contentHashCode()
        result = 31 * result + metadata.contentHashCode()
        return result
    }
}
