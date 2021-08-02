package com.breader.dddbuildingblocks.guitar

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.random.Random

internal class GuitarTest {

    private val guitarId = GuitarId(UUID.randomUUID())

    private val neckPickup = Pickup.ofTypeAndPos(PickupType.DOUBLE_COIL, PickupPosition.NECK)
    private val bridgePickup = Pickup.ofTypeAndPos(PickupType.DOUBLE_COIL, PickupPosition.BRIDGE)
    private val pickups = Pickups.with(listOf(neckPickup, bridgePickup), neckPickup)

    private val soloSection = SongPart(
        notes = List(150) { Note.ofHeight(Random.nextInt(0, 12)) },
        meter = Meter(4, 4),
        tempo = 160,
        tuning = Tuning.STANDARD,
        songType = SongPartType.SOLO
    )

    private val rhythmSection = SongPart(
        notes = List(150) { Note.ofHeight(Random.nextInt(0, 12)) },
        meter = Meter(4, 4),
        tempo = 120,
        tuning = Tuning.STANDARD,
        songType = SongPartType.RHYTHM
    )

    @Test
    fun should_play_rhythm_part_when_it_is_doable() {
        // given
        val guitar = Guitar(
            guitarId,
            pickups,
            listOf(Tuning.STANDARD, Tuning.DROP_D),
            Tuning.STANDARD,
            Knob.setTo(100),
            Knob.setTo(100)
        )

        // when
        val result = guitar.playRhythmPart(rhythmSection)

        // then
        assertEquals(Result.OK, result)
    }

    @Test
    fun should_not_play_rhythm_part_when_tone_cannot_be_adjusted() {
        // given

        // when

        // then
    }

    @Test
    fun should_not_play_rhythm_part_when_proper_pickup_is_not_available() {
        // given

        // when

        // then
    }

    @Test
    fun should_not_play_rhythm_part_when_guitar_is_out_of_tune() {
        // given
        val guitar = Guitar(
            guitarId,
            pickups,
            listOf(Tuning.DROP_C, Tuning.DROP_A),
            Tuning.STANDARD,
            Knob.setTo(100),
            Knob.setTo(100)
        )

        // when
        val result = guitar.playRhythmPart(rhythmSection)

        // then
        assertEquals(Result.DESIRED_TUNING_NOT_AVAILABLE, result)
    }

    @Test
    fun should_play_solo_part_when_it_is_doable() {
        // given
        val guitar = Guitar(
            guitarId,
            pickups,
            listOf(Tuning.STANDARD, Tuning.DROP_D),
            Tuning.STANDARD,
            Knob.setTo(100),
            Knob.setTo(100)
        )

        // when
        val result = guitar.playSolo(soloSection)

        // then
        assertEquals(Result.OK, result)
    }

    @Test
    fun should_not_play_solo_part_when_tone_cannot_be_adjusted() {
        // given

        // when

        // then
    }

    @Test
    fun should_not_play_solo_part_when_proper_pickup_is_not_available() {
        // given

        // when

        // then
    }

    @Test
    fun should_not_play_solo_part_when_guitar_is_out_of_tune() {
        // given
        val guitar = Guitar(
            guitarId,
            pickups,
            listOf(Tuning.DROP_C, Tuning.DROP_A),
            Tuning.STANDARD,
            Knob.setTo(100),
            Knob.setTo(100)
        )

        // when
        val result = guitar.playSolo(soloSection)

        // then
        assertEquals(Result.DESIRED_TUNING_NOT_AVAILABLE, result)
    }

}
