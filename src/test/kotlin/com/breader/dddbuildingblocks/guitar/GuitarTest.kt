package com.breader.dddbuildingblocks.guitar

import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class GuitarTest {

    val soloSection = SongPart(
        notes = List(150) { Note.ofHeight(Random.nextInt(0, 12)) },
        meter = Meter(4, 4),
        tempo = 160,
        tuning = Tuning.STANDARD
    )

    val rhythmSection = SongPart(
        notes = List(150) { Note.ofHeight(Random.nextInt(0, 12)) },
        meter = Meter(4, 4),
        tempo = 120,
        tuning = Tuning.STANDARD
    )

    @Test
    fun should_play_rhythm_part_when_it_is_doable() {

    }

    @Test
    fun should_not_play_rhythm_part_when_tone_cannot_be_adjusted() {

    }

    @Test
    fun should_not_play_rhythm_part_when_proper_pickup_is_not_available() {

    }

    @Test
    fun should_not_play_rhythm_part_when_guitar_is_out_of_tune() {

    }

    @Test
    fun should_play_solo_part_when_it_is_doable() {

    }

    @Test
    fun should_not_play_solo_part_when_tone_cannot_be_adjusted() {

    }

    @Test
    fun should_not_play_solo_part_when_proper_pickup_is_not_available() {

    }

    @Test
    fun should_not_play_solo_part_when_guitar_is_out_of_tune() {

    }

}
