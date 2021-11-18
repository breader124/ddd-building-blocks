package com.breader.dddbuildingblocks.guitar

import com.breader.dddbuildingblocks.common.Specification
import com.breader.dddbuildingblocks.guitar.model.*
import com.breader.dddbuildingblocks.guitar.model.specification.ToneCheckCode
import com.breader.dddbuildingblocks.guitar.model.specification.ToneCheckResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class GuitarTest {

    private val guitarId = GuitarId()

    private val neckPickup = Pickup.ofTypeAndPos(PickupType.DOUBLE_COIL, PickupPosition.NECK)
    private val bridgePickup = Pickup.ofTypeAndPos(PickupType.DOUBLE_COIL, PickupPosition.BRIDGE)
    private val pickups = Pickups.with(listOf(neckPickup, bridgePickup), neckPickup)

    private val tunings = Tunings.with(listOf(Tuning.STANDARD, Tuning.DROP_D), Tuning.STANDARD)

    private var volumeKnob = Knob.withLevel(100)
    private val toneKnob = Knob.withLevel(100)

    private val guitar = Guitar(guitarId, tunings, pickups, volumeKnob, toneKnob)

    private val reachableToneSpec = Specification<Guitar, ToneCheckResult> {
        ToneCheckResult(listOf(ToneCheckCode.OK), null, null, null)
    }

    private val playableSection = PartToPlay(Tuning.STANDARD, reachableToneSpec)
    private val invalidTuningSection = PartToPlay(Tuning.DROP_C, reachableToneSpec)

    @Test
    fun should_play_song_when_guitar_is_in_proper_state() {
        assertDoesNotThrow {
            guitar.playSong(playableSection)
        }
    }

    @Test
    fun should_not_play_song_when_guitar_cannot_be_tuned_properly() {
        assertThrows<IllegalArgumentException> {
            guitar.playSong(invalidTuningSection)
        }
    }

    @Test
    fun should_play_warmup_without_any_complaints() {
        // given
        val volumeKnob = guitar.volumeKnob
        guitar.volumeKnob = volumeKnob.adjustLevel(0)

        // when + then
        assertDoesNotThrow {
            guitar.playSong(playableSection)
        }

        // finally
        guitar.volumeKnob = volumeKnob
    }

}
