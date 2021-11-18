package com.breader.dddbuildingblocks.guitar.application

import com.breader.dddbuildingblocks.guitar.model.Guitars
import com.breader.dddbuildingblocks.guitar.model.PartToPlay
import com.breader.dddbuildingblocks.guitar.model.ToneSpec

class PlayingSong(private val guitars: Guitars) {

    fun handle(command: PlayingSongCommand) {
        val guitar = guitars.findById(command.guitarId) ?: throw NoSuchElementException()

        val partToPlay = preparePartToPlay(command)
        guitar.playSong(partToPlay)

        guitars.save(guitar)
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
