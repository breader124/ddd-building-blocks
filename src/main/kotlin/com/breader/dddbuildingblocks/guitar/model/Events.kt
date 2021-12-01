package com.breader.dddbuildingblocks.guitar.model

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import java.time.Instant
import java.util.*


class GuitarManufactured(
    val tunings: Tunings,
    val pickups: Pickups,
    val volumeKnob: Knob,
    val toneKnob: Knob,
    version: Int
) : DomainEvent("guitar_manufactured_v1", version)

class Tuned(
    val from: Tuning,
    val to: Tuning,
    version: Int
) : DomainEvent("guitar_tuned_v1", version)

class VolKnobAdjusted(
    val from: Int,
    val to: Int,
    version: Int
) : DomainEvent("vol_knob_adjusted_v1", version)

class ToneKnobAdjusted(
    val from: Int,
    val to: Int,
    version: Int
) : DomainEvent("tone_knob_adjusted_v1", version)

class PickupSwitched(
    val from: Pickup,
    val to: Pickup,
    version: Int
) : DomainEvent("pickup_switched_v1", version)

class SongPlayed(version: Int) : DomainEvent("song_played_v1", version)
