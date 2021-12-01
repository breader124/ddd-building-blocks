package com.breader.dddbuildingblocks.guitar.application

import com.breader.dddbuildingblocks.common.event.publishing.domain.EventPublisher
import com.breader.dddbuildingblocks.guitar.model.Guitars
import com.breader.dddbuildingblocks.guitar.model.PartToPlay
import com.breader.dddbuildingblocks.guitar.model.ToneSpec

class PlayingSong(
    private val guitars: Guitars,
    private val eventPublisher: EventPublisher
) {

    fun handle(command: PlayingSongCommand) {
        val guitar = guitars.findById(command.guitarId) ?: throw NoSuchElementException()

        val partToPlay = preparePartToPlay(command)
        val events = guitar.playSong(partToPlay)

        eventPublisher.publish(guitar.guitarId.id, guitar.version, events)
    }

    private fun preparePartToPlay(command: PlayingSongCommand): PartToPlay {
        val customToneSpec = ToneSpec.Builder()
            .pickup(command.pickup)
            .toneLevel(command.toneLevel)
            .volumeLevel(command.volumeLevel)
            .build()
        return PartToPlay(command.tuning, customToneSpec)
    }

}
