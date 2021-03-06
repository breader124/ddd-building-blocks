package com.breader.dddbuildingblocks.guitar.model

class Tunings private constructor(val available: List<Tuning>, val chosen: Tuning) {
    companion object {
        fun with(available: List<Tuning>, chosen: Tuning): Tunings {
            if (available.isEmpty()) throw IllegalArgumentException("You must pass at least one tuning")
            if (!available.contains(chosen)) throw IllegalArgumentException("Tuning list must contain chosen one")
            return Tunings(available, chosen)
        }
    }

    fun tune(chosen: Tuning): Tunings {
        if (!available.contains(chosen)) throw IllegalArgumentException("Chosen tuning not available")
        return Tunings(this.available, chosen)
    }
}

enum class Tuning {
    STANDARD,
    OPEN_A, OPEN_B, OPEN_C, OPEN_D, OPEN_E, OPEN_F, OPEN_G,
    DROP_A, DROP_B, DROP_C, DROP_D, DROP_E, DROP_F, DROP_G
}
