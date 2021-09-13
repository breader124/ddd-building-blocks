package com.breader.dddbuildingblocks.guitar.model

import java.util.*

class GuitarId {
    val id: UUID

    init {
        id = UUID.randomUUID()
    }
}

