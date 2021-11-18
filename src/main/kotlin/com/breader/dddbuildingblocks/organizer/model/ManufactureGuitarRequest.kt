package com.breader.dddbuildingblocks.organizer.model

import com.breader.dddbuildingblocks.guitar.application.ManufactureGuitarCommand
import com.breader.dddbuildingblocks.guitar.model.*

data class ManufactureGuitarRequest(
    val availableTunings: List<String>,
    val chosenTuning: String,
    val availablePickups: List<PickupDTO>,
    val chosenPickup: PickupDTO,
    val volumeKnobLevel: Int,
    val toneKnobLevel: Int
) {

    fun toCommand(): ManufactureGuitarCommand {
        val availableTunings = mutableListOf<Tuning>()
        this.availableTunings.forEach { availableTunings.add(Tuning.valueOf(it)) }
        val chosenTuning = Tuning.valueOf(chosenTuning)

        val availablePickups = mutableListOf<Pickup>()
        this.availablePickups.forEach {
            availablePickups.add(Pickup.ofTypeAndPos(PickupType.valueOf(it.type), PickupPosition.valueOf(it.pos)))
        }
        val chosenPickup = Pickup.ofTypeAndPos(
            PickupType.valueOf(this.chosenPickup.type), PickupPosition.valueOf(this.chosenPickup.pos)
        )

        return ManufactureGuitarCommand(
            tunings = Tunings.with(availableTunings, chosenTuning),
            pickups = Pickups.with(availablePickups, chosenPickup),
            volumeKnobLevel = volumeKnobLevel,
            toneKnobLevel = toneKnobLevel
        )
    }

}
