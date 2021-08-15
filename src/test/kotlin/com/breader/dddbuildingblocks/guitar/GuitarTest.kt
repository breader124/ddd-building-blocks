package com.breader.dddbuildingblocks.guitar

import com.breader.dddbuildingblocks.common.Specification
import com.breader.dddbuildingblocks.guitar.specification.ToneCheckCode
import com.breader.dddbuildingblocks.guitar.specification.ToneCheckResult
import com.breader.dddbuildingblocks.guitar.specification.warmupToneSpecification
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GuitarTest {

    private val guitarId = GuitarId()

    private val neckPickup = Pickup.ofTypeAndPos(PickupType.DOUBLE_COIL, PickupPosition.NECK)
    private val bridgePickup = Pickup.ofTypeAndPos(PickupType.DOUBLE_COIL, PickupPosition.BRIDGE)
    private val pickups = Pickups.with(listOf(neckPickup, bridgePickup), neckPickup)

    private val tunings = Tunings.with(listOf(Tuning.STANDARD, Tuning.DROP_D), Tuning.STANDARD)

    private var volumeKnob = Knob.withLevel(100)
    private val toneKnob = Knob.withLevel(100)

    private val guitar = Guitar(guitarId, tunings, pickups, volumeKnob, toneKnob)

    private val notReachableToneSpec = Specification<Guitar, ToneCheckResult> {
        ToneCheckResult(ToneCheckCode.TONE_NOT_ADJUSTABLE, null, null, null)
    }
    private val reachableToneSpec = Specification<Guitar, ToneCheckResult> {
        ToneCheckResult(ToneCheckCode.OK, null, null, null)
    }

    private val warmupSection = PartToPlay(Tuning.STANDARD, warmupToneSpecification)
    private val playableSection = PartToPlay(Tuning.STANDARD, reachableToneSpec)
    private val invalidTuningSection = PartToPlay(Tuning.DROP_C, reachableToneSpec)
    private val invalidToneSpecSection = PartToPlay(Tuning.STANDARD, notReachableToneSpec)

    @Test
    fun should_play_song_when_guitar_is_in_proper_state() {
        // given + when
        val result = guitar.playSong(playableSection)

        // then
        assertEquals(Result.OK, result)
    }

    @Test
    fun should_not_play_song_when_tone_is_not_adjustable() {
        // given + when
        val result = guitar.playSong(invalidToneSpecSection)

        // then
        assertEquals(Result.TONE_NOT_ADJUSTABLE, result)
    }

    @Test
    fun should_not_play_song_when_guitar_cannot_be_tuned_properly() {
        // given + when
        val result = guitar.playSong(invalidTuningSection)

        // then
        assertEquals(Result.DESIRED_TUNING_NOT_AVAILABLE, result)
    }

    @Test
    fun should_play_warmup_when_guitar_is_in_proper_state() {
        // given
        val volumeKnob = guitar.volumeKnob
        guitar.volumeKnob = volumeKnob.adjustLevel(0)

        // when
        val result = guitar.playWarmUp(warmupSection)

        // then
        assertEquals(Result.OK, result)

        // finally
        guitar.volumeKnob = volumeKnob
    }

    @Test
    fun should_not_play_warmup_when_guitar_is_not_muted() {
        // given + when
        val result = guitar.playWarmUp(warmupSection)

        // then
        assertEquals(Result.TONE_NOT_ADJUSTABLE, result)
    }

}
