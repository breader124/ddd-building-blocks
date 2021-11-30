package com.breader.dddbuildingblocks.guitar.application

import com.breader.dddbuildingblocks.common.event.publishing.domain.EventPublisher
import com.breader.dddbuildingblocks.guitar.model.GuitarId
import com.breader.dddbuildingblocks.guitar.model.GuitarManufactured
import com.breader.dddbuildingblocks.guitar.model.Knob

class ManufacturingGuitar(
    private val eventPublisher: EventPublisher
) {

    fun handle(command: ManufactureGuitarCommand): GuitarId {
        val newGuitarId = GuitarId()
        val guitarManufactured = GuitarManufactured(
            tunings = command.tunings,
            pickups = command.pickups,
            volumeKnob = Knob.withLevel(command.volumeKnobLevel),
            toneKnob = Knob.withLevel(command.toneKnobLevel)
        )
        eventPublisher.publish(newGuitarId.id, listOf(guitarManufactured))
        return newGuitarId
    }

}
