package com.breader.dddbuildingblocks.organizer.model

import com.breader.dddbuildingblocks.guitar.model.Guitar
import org.springframework.hateoas.RepresentationModel

data class GuitarDTO(
    val availableTunings: List<String>,
    val chosenTuning: String,
    val availablePickups: List<PickupDTO>,
    val chosenPickup: PickupDTO,
    val volumeKnobLevel: Int,
    val toneKnobLevel: Int
) : RepresentationModel<GuitarDTO>() {

    companion object {
        fun fromDomainModel(guitar: Guitar): GuitarDTO {
            val availableTunings = mutableListOf<String>()
            guitar.tunings.available.forEach { availableTunings.add(it.name) }

            val availablePickups = mutableListOf<PickupDTO>()
            guitar.pickups.available.forEach { availablePickups.add(PickupDTO(it.pickupType.name, it.pickupPosition.name)) }

            val chosenPickup = guitar.pickups.chosen
            return GuitarDTO(
                availableTunings,
                guitar.tunings.chosen.name,
                availablePickups,
                PickupDTO(chosenPickup.pickupType.name, chosenPickup.pickupPosition.name),
                guitar.volumeKnob.level,
                guitar.toneKnob.level
            )
        }

    }

}