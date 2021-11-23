package com.breader.dddbuildingblocks.common.event.storage.domain

import java.util.concurrent.CompletableFuture

interface StorageClient {
    fun store(streamName: String, event: PersistableEvent): CompletableFuture<Unit>
}
