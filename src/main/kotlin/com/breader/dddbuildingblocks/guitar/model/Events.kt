package com.breader.dddbuildingblocks.guitar.model

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import java.time.Instant
import java.util.*


class GuitarManufactured(
    val tunings: Tunings,
    val pickups: Pickups,
    val volumeKnob: Knob,
    val toneKnob: Knob,
) : DomainEvent("guitar_manufactured_v1")

class Tuned(
    val from: Tuning,
    val to: Tuning
) : DomainEvent("guitar_tuned_v1")

class VolKnobAdjusted(
    val from: Int,
    val to: Int,
) : DomainEvent("vol_knob_adjusted_v1")

class ToneKnobAdjusted(
    val from: Int,
    val to: Int,
) : DomainEvent("tone_knob_adjusted_v1")

class PickupSwitched(
    val from: Pickup,
    val to: Pickup,
) : DomainEvent("pickup_switched_v1")

class SongPlayed : DomainEvent("song_played_v1")
