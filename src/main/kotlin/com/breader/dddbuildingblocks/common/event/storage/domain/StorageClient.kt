package com.breader.dddbuildingblocks.common.event.storage.domain

interface StorageClient {
    fun store(streamName: String, event: PersistableEvent)
}
