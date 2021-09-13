package com.breader.dddbuildingblocks.guitar.model


class Knob private constructor(val level: Int) {
    companion object {
        fun withLevel(level: Int): Knob {
            if (level !in 0..100) {
                throw IllegalArgumentException("Level was $level, it should be in <0, 100> range")
            }
            return Knob(level)
        }
    }

    fun adjustLevel(level: Int): Knob {
        if (level !in 0..100) {
            throw IllegalArgumentException("Level was $level, it should be in <0, 100> range")
        }
        return Knob(level)
    }
}
