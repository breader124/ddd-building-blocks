package com.breader.dddbuildingblocks.guitar

data class SongPart(
    val notes: List<Note>,
    val meter: Meter,
    val tempo: Int
)

data class Meter(val numerator: Int, val denominator: Int)
