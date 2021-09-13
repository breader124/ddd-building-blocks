package com.breader.dddbuildingblocks.guitar.application

import com.breader.dddbuildingblocks.common.Specification
import com.breader.dddbuildingblocks.guitar.model.Guitar
import com.breader.dddbuildingblocks.guitar.model.Guitars
import com.breader.dddbuildingblocks.guitar.model.PartToPlay
import com.breader.dddbuildingblocks.guitar.model.Result.DESIRED_TUNING_NOT_AVAILABLE
import com.breader.dddbuildingblocks.guitar.model.Result.TONE_NOT_ADJUSTABLE
import com.breader.dddbuildingblocks.guitar.model.specification.ToneCheckCode
import com.breader.dddbuildingblocks.guitar.model.specification.ToneCheckResult

class PlayingSong(private val guitars: Guitars) {

    // TODO make sure that processing is transactional
    fun handle(command: PlayingSongCommand) {
        val guitar = guitars.findById(command.guitarId) ?: throw NoSuchElementException()

        val customToneSpec = Specification<Guitar, ToneCheckResult> {
            if (it.volumeKnob.level == command.volumeLevel && it.toneKnob.level == command.toneLevel) {
                ToneCheckResult(listOf(ToneCheckCode.OK), null, null, null)
            } else if (it.volumeKnob.level == command.volumeLevel) {
                ToneCheckResult(listOf(ToneCheckCode.INVALID_TONE_KNOB_LEVEL), null, command.toneLevel, null)
            } else if (it.toneKnob.level == command.toneLevel) {
                ToneCheckResult(listOf(ToneCheckCode.INVALID_VOL_KNOB_LEVEL), null, null, command.volumeLevel)
            } else {
                ToneCheckResult(listOf(ToneCheckCode.INVALID_TONE_KNOB_LEVEL, ToneCheckCode.INVALID_VOL_KNOB_LEVEL), null, command.toneLevel, command.volumeLevel)
            }
        }
        val partToPlay = PartToPlay(command.tuning, customToneSpec)

        val result = guitar.playSong(partToPlay)
        if (result == DESIRED_TUNING_NOT_AVAILABLE || result == TONE_NOT_ADJUSTABLE) {
            throw IllegalArgumentException()
        }

        guitars.save(guitar)
    }

}
