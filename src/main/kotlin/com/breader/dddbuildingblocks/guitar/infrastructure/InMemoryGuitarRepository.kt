package com.breader.dddbuildingblocks.guitar.infrastructure

import com.breader.dddbuildingblocks.guitar.model.Guitar
import com.breader.dddbuildingblocks.guitar.model.GuitarId
import com.breader.dddbuildingblocks.guitar.model.Guitars

class InMemoryGuitarRepository : Guitars {

    private val memory = mutableListOf<Guitar>()

    override fun findById(id: GuitarId): Guitar {
        return memory.find { it.guitarId.id == id.id } ?: throw NoSuchElementException("No such element with id=${id.id}")
    }

    override fun save(guitar: Guitar) {
        memory.add(guitar)
    }

}
