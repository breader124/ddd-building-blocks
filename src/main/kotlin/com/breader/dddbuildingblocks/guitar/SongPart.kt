package com.breader.dddbuildingblocks.guitar

data class SongPart(
    val notes: List<Note>,
    val meter: Meter,
    val tempo: Int,
    val tuning: Tuning,
    val songType: SongPartType
)

data class Meter(val numerator: Int, val denominator: Int)

enum class SongPartType {
    RHYTHM, SOLO
}
