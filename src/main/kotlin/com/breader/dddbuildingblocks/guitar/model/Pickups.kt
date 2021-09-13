package com.breader.dddbuildingblocks.guitar.model

class Pickups private constructor(private val available: List<Pickup>, val chosen: Pickup) {
    companion object {
        fun with(p: List<Pickup>, chosen: Pickup): Pickups {
            if (p.isEmpty()) throw IllegalArgumentException("You must pass at least one pickup")
            PickupPosition.values().forEach {
                val num = p.count { pickup -> pickup.pickupPosition == it }
                if (num > 1) {
                    throw IllegalArgumentException("Pickups list contains more than one pickup per position")
                }
            }
            return Pickups(p, p[0])
        }
    }

    fun switch(chosen: Pickup): Pickups {
        if (!available.contains(chosen)) throw IllegalArgumentException("Chosen pickup not available")
        return Pickups(this.available, chosen)
    }
}

class Pickup private constructor(val pickupType: PickupType, val pickupPosition: PickupPosition) {
    companion object {
        fun ofTypeAndPos(type: PickupType, pos: PickupPosition): Pickup {
            return Pickup(type, pos)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Pickup) {
            return false
        }
        return pickupType == other.pickupType && pickupPosition == other.pickupPosition
    }

    override fun hashCode(): Int {
        var result = pickupType.hashCode()
        result = 31 * result + pickupPosition.hashCode()
        return result
    }
}

enum class PickupType {
    SINGLE_COIL, DOUBLE_COIL
}

enum class PickupPosition {
    BRIDGE, MIDDLE, NECK
}
