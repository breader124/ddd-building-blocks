package com.breader.dddbuildingblocks.guitar.model

import com.breader.dddbuildingblocks.guitar.model.specification.ToneCheckCode
import com.breader.dddbuildingblocks.guitar.model.specification.ToneCheckResult
import com.breader.dddbuildingblocks.guitar.model.specification.warmupToneSpecification

class Guitar(
    val id: GuitarId,
    var tunings: Tunings,
    var pickups: Pickups,
    var volumeKnob: Knob,
    var toneKnob: Knob
) {

    fun playSong(partToPlay: PartToPlay) {
        tuneFor(partToPlay)
        adjustToneFor(partToPlay)
    }

    private fun tuneFor(partToPlay: PartToPlay){
        val desiredTuning = partToPlay.tuning
        tunings = tunings.tune(desiredTuning)
    }

    private fun adjustToneFor(partToPlay: PartToPlay): Boolean {
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
        // checking for status code below might be also solved by visitor pattern, for sake of not overcomplicating it
        // all, it is done by just using several ifs, whenever this class grows, this is first place for refactoring
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
                    pickups = pickups.switch(pickup)
                    existingProblems.remove(it)
                }
            }
        }
        return existingProblems
    }

    fun playWarmUp(){
        val toneCheckResult = warmupToneSpecification.isSatisfiedBy(this)
        if (toneCheckResult.codes.contains(ToneCheckCode.INVALID_VOL_KNOB_LEVEL)) {
            volumeKnob = Knob.withLevel(0)
        }
    }

}
