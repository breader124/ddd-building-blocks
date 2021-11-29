package com.breader.dddbuildingblocks.guitar.infrastructure

import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.breader.dddbuildingblocks.common.event.storage.infrastructure.EventMapper
import com.breader.dddbuildingblocks.guitar.model.Guitar
import com.breader.dddbuildingblocks.guitar.model.GuitarId
import com.breader.dddbuildingblocks.guitar.model.Guitars

class EventStoreGuitarRepository(
    private val storageClient: StorageClient,
    private val eventMapper: EventMapper
) : Guitars {

    override fun findById(id: GuitarId): Guitar? {
        TODO("Implement me")
    }

}
