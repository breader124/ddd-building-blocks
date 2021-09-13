package com.breader.dddbuildingblocks.guitar.model

interface Guitars {
    fun findById(id: GuitarId): Guitar?
    fun save(guitar: Guitar)
}
