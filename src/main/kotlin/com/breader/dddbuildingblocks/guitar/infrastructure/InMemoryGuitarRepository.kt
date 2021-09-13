package com.breader.dddbuildingblocks.guitar.infrastructure

import com.breader.dddbuildingblocks.guitar.model.Guitar
import com.breader.dddbuildingblocks.guitar.model.GuitarId
import com.breader.dddbuildingblocks.guitar.model.Guitars

class InMemoryGuitarRepository : Guitars {
    override fun findById(id: GuitarId): Guitar {
        TODO("Not yet implemented")
    }

    override fun save(guitar: Guitar) {
        TODO("Not yet implemented")
    }
}
