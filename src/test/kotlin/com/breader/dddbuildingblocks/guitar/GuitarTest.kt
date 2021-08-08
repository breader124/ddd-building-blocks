package com.breader.dddbuildingblocks.guitar

import org.junit.jupiter.api.Test
import java.util.*

internal class GuitarTest {

    private val guitarId = GuitarId(UUID.randomUUID())

    private val neckPickup = Pickup.ofTypeAndPos(PickupType.DOUBLE_COIL, PickupPosition.NECK)
    private val bridgePickup = Pickup.ofTypeAndPos(PickupType.DOUBLE_COIL, PickupPosition.BRIDGE)
    private val pickups = Pickups.with(listOf(neckPickup, bridgePickup), neckPickup)

    private val standardTuningSection = PartToPlay(Tuning.STANDARD)
    private val dropCTuningSection = PartToPlay(Tuning.DROP_C)

    @Test
    fun should_play_song_when_guitar_is_in_proper_state() {
        TODO()
    }

    @Test
    fun should_not_play_song_when_tone_is_not_adjustable() {
        TODO()
    }

    @Test
    fun should_not_play_song_when_guitar_cannot_be_tuned_properly() {
        TODO()
    }

    @Test
    fun should_play_warmup_when_guitar_is_in_proper_state() {
        TODO()
    }

    @Test
    fun should_not_play_warmup_when_guitar_is_not_muted() {
        TODO()
    }

}
