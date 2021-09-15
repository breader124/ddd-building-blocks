package com.breader.dddbuildingblocks.guitar.application

import com.breader.dddbuildingblocks.guitar.model.Guitars
import com.breader.dddbuildingblocks.guitar.model.PartToPlay
import com.breader.dddbuildingblocks.guitar.model.Result.DESIRED_TUNING_NOT_AVAILABLE
import com.breader.dddbuildingblocks.guitar.model.Result.TONE_NOT_ADJUSTABLE
import com.breader.dddbuildingblocks.guitar.model.ToneSpec

class PlayingSong(private val guitars: Guitars) {

    fun handle(command: PlayingSongCommand) {
        val guitar = guitars.findById(command.guitarId) ?: throw NoSuchElementException()

        val partToPlay = preparePartToPlay(command)
        val result = guitar.playSong(partToPlay)
        if (result == DESIRED_TUNING_NOT_AVAILABLE || result == TONE_NOT_ADJUSTABLE) {
            throw IllegalArgumentException()
        }

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
