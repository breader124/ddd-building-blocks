package com.breader.dddbuildingblocks.common.event.storage.domain

import java.util.*

interface StorageClient {
    fun store(streamId: UUID, events: List<PersistableEvent>)
    fun fetch(streamId: UUID): List<PersistableEvent>
}
