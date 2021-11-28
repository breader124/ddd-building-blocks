package com.breader.dddbuildingblocks.guitar.application

import com.breader.dddbuildingblocks.guitar.model.Guitar
import com.breader.dddbuildingblocks.guitar.model.GuitarId
import com.breader.dddbuildingblocks.guitar.model.Guitars
import com.breader.dddbuildingblocks.guitar.model.Knob

class ManufacturingGuitar(private val guitars: Guitars) {

    fun handle(command: ManufactureGuitarCommand): GuitarId {
        val newGuitarId = GuitarId()
        val guitar = Guitar(
            guitarId = newGuitarId,
            tunings = command.tunings,
            pickups = command.pickups,
            volumeKnob = Knob.withLevel(command.volumeKnobLevel),
            toneKnob = Knob.withLevel(command.toneKnobLevel),
            version = 0
        )
        guitars.save(guitar)
        return newGuitarId
    }

}
