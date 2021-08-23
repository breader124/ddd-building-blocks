package com.breader.dddbuildingblocks.guitar

import com.breader.dddbuildingblocks.guitar.specification.ToneCheckCode
import com.breader.dddbuildingblocks.guitar.specification.ToneCheckResult
import com.breader.dddbuildingblocks.guitar.specification.warmupToneSpecification

class Guitar(
    val id: GuitarId,
    var tunings: Tunings,
    var pickups: Pickups,
    var volumeKnob: Knob,
    var toneKnob: Knob
) {

    fun playSong(partToPlay: PartToPlay): Result {
        if (!isGuitarInTune(partToPlay)) return Result.DESIRED_TUNING_NOT_AVAILABLE
        if (!checkTone(partToPlay)) return Result.TONE_NOT_ADJUSTABLE
        return Result.OK
    }

    private fun isGuitarInTune(partToPlay: PartToPlay): Boolean {
        val desiredTuning = partToPlay.tuning
        try {
            tunings = tunings.tune(desiredTuning)
        } catch (exc: IllegalArgumentException) {
            return false
        }
        return true
    }

    private fun checkTone(partToPlay: PartToPlay): Boolean {
        val toneSpecResult = identifyProblemsWithTone(partToPlay)
        if (isThereNoProblemsWithTone(toneSpecResult)) {
            return true
        }
        val existingProblems = tryToSolveProblems(toneSpecResult)
        return existingProblems.isEmpty()
    }

    private fun identifyProblemsWithTone(partToPlay: PartToPlay): ToneCheckResult {
        val toneSpec = partToPlay.toneSpec
        return toneSpec.isSatisfiedBy(this)
    }

    private fun isThereNoProblemsWithTone(toneSpecResult: ToneCheckResult) =
        toneSpecResult.codes.size == 1 && toneSpecResult.codes[0] == ToneCheckCode.OK

    private fun tryToSolveProblems(toneSpecResult: ToneCheckResult): MutableList<ToneCheckCode> {
        val existingProblems = toneSpecResult.codes.toMutableList()
        // checking for status code below might be also solved by visitor pattern for sake of not overcomplicating it
        // all it is done by just using several ifs
        toneSpecResult.codes.forEach {
            if (it == ToneCheckCode.INVALID_VOL_KNOB_LEVEL) {
                toneSpecResult.desiredVolKnobLevel?.also { level ->
                    volumeKnob = Knob.withLevel(level)
                    existingProblems.remove(it)
                }
            }
            if (it == ToneCheckCode.INVALID_TONE_KNOB_LEVEL) {
                toneSpecResult.desiredToneKnobLevel?.also { level ->
                    toneKnob = Knob.withLevel(level)
                    existingProblems.remove(it)
                }
            }
            if (it == ToneCheckCode.WRONG_PICKUP_CHOSEN) {
                toneSpecResult.desiredPickup?.also { pickup ->
                    pickups.switch(pickup)
                    existingProblems.remove(it)
                }
            }
        }
        return existingProblems
    }

    fun playWarmUp(): Result {
        val toneCheckResult = warmupToneSpecification.isSatisfiedBy(this)
        if (toneCheckResult.codes.contains(ToneCheckCode.INVALID_VOL_KNOB_LEVEL)) {
            volumeKnob = Knob.withLevel(0)
        }
        return Result.OK
    }

}
