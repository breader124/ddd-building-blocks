package com.breader.dddbuildingblocks.organizer.model

interface Request <T> {
    fun toCommand(): T
}