package com.breader.dddbuildingblocks.common.event.storage.infrastructure

import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.breader.dddbuildingblocks.common.event.storage.domain.PersistableEvent

class EventStoreStorageClient : StorageClient {

    override fun store(event: PersistableEvent) {
        TODO("Not yet implemented")
    }

}
