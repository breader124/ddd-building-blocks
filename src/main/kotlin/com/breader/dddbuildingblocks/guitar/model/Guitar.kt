package com.breader.dddbuildingblocks.guitar.model

import com.breader.dddbuildingblocks.common.aggregate.AggregateRoot
import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import com.breader.dddbuildingblocks.guitar.model.specification.ToneCheckCode
import com.breader.dddbuildingblocks.guitar.model.specification.ToneCheckResult
import java.time.Instant

class Guitar(
    val guitarId: GuitarId,
    var tunings: Tunings,
    var pickups: Pickups,
    var volumeKnob: Knob,
    var toneKnob: Knob,
    version: Int,
) : AggregateRoot(version) {

    fun apply(tunedEvent: Tuned) {
        tunings = tunings.tune(tunedEvent.to)
    }

    fun apply(volKnobAdjusted: VolKnobAdjusted) {
        volumeKnob = volumeKnob.adjustLevel(volKnobAdjusted.to)
    }

    fun apply(toneKnobAdjusted: ToneKnobAdjusted) {
        toneKnob = toneKnob.adjustLevel(toneKnobAdjusted.to)
    }

    fun apply(pickupSwitched: PickupSwitched) {
        pickups = pickups.switch(pickupSwitched.to)
    }

    fun playSong(partToPlay: PartToPlay): List<DomainEvent> {
        tuneFor(partToPlay)
        adjustToneFor(partToPlay)
        domainEvents.add(SongPlayed(guitarId.id, Instant.now(), version))
        return domainEvents
    }

    private fun tuneFor(partToPlay: PartToPlay) {
        val actualTuning = tunings.chosen
        val desiredTuning = partToPlay.tuning
        if (actualTuning != desiredTuning) {
            tunings = tunings.tune(desiredTuning)
            domainEvents.add(Tuned(guitarId.id, Instant.now(), version, actualTuning, desiredTuning))
        }
    }

    private fun adjustToneFor(partToPlay: PartToPlay) {
        val toneSpecResult = identifyProblemsWithTone(partToPlay)
        if (isThereNoProblemsWithTone(toneSpecResult)) {
            return
        }
        val existingProblems = tryToSolveProblems(toneSpecResult)
        if (existingProblems.isNotEmpty()) {
            throw IllegalStateException("There are still issues with tone setup")
        }
    }

    private fun identifyProblemsWithTone(partToPlay: PartToPlay): ToneCheckResult {
        val toneSpec = partToPlay.toneSpec
        return toneSpec.isSatisfiedBy(this)
    }

    private fun isThereNoProblemsWithTone(toneSpecResult: ToneCheckResult): Boolean =
        toneSpecResult.codes.size == 1 && toneSpecResult.codes[0] == ToneCheckCode.OK

    private fun tryToSolveProblems(toneSpecResult: ToneCheckResult): MutableList<ToneCheckCode> {
        val existingProblems = toneSpecResult.codes.toMutableList()
        // checking for status code below might be also solved by visitor pattern, for sake of not overcomplicating it
        // all, it is done by just using several ifs, whenever this class grows, this is first place for refactoring
        toneSpecResult.codes.forEach {
            if (it == ToneCheckCode.INVALID_VOL_KNOB_LEVEL) {
                toneSpecResult.desiredVolKnobLevel?.also { level ->
                    val actualVolumeLevel = volumeKnob.level
                    volumeKnob = Knob.withLevel(level)
                    domainEvents.add(VolKnobAdjusted(guitarId.id, Instant.now(), version, actualVolumeLevel, level))
                    existingProblems.remove(it)
                }
            }
            if (it == ToneCheckCode.INVALID_TONE_KNOB_LEVEL) {
                toneSpecResult.desiredToneKnobLevel?.also { level ->
                    val actualToneKnobLevel = toneKnob.level
                    toneKnob = Knob.withLevel(level)
                    domainEvents.add(ToneKnobAdjusted(guitarId.id, Instant.now(), version, actualToneKnobLevel, level))
                    existingProblems.remove(it)
                }
            }
            if (it == ToneCheckCode.WRONG_PICKUP_CHOSEN) {
                toneSpecResult.desiredPickup?.also { pickup ->
                    val actuallyChosenPickup = pickups.chosen
                    pickups = pickups.switch(pickup)
                    domainEvents.add(PickupSwitched(guitarId.id, Instant.now(), version, actuallyChosenPickup, pickup))
                    existingProblems.remove(it)
                }
            }
        }
        return existingProblems
    }

}
