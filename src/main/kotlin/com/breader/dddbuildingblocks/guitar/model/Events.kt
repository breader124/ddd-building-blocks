package com.breader.dddbuildingblocks.guitar.model

import com.breader.dddbuildingblocks.common.event.publishing.domain.DomainEvent
import java.time.Instant
import java.util.*


class GuitarManufactured(
    val tunings: Tunings,
    val pickups: Pickups,
    val volumeKnob: Knob,
    val toneKnob: Knob,
    aggregateVersion: Int
) : DomainEvent("guitar_manufactured_v1", aggregateVersion)

class Tuned(
    val from: Tuning,
    val to: Tuning,
    aggregateVersion: Int
) : DomainEvent("guitar_tuned_v1", aggregateVersion)

class VolKnobAdjusted(
    val from: Int,
    val to: Int,
    aggregateVersion: Int
) : DomainEvent("vol_knob_adjusted_v1", aggregateVersion)

class ToneKnobAdjusted(
    val from: Int,
    val to: Int,
    aggregateVersion: Int
) : DomainEvent("tone_knob_adjusted_v1", aggregateVersion)

class PickupSwitched(
    val from: Pickup,
    val to: Pickup,
    aggregateVersion: Int
) : DomainEvent("pickup_switched_v1", aggregateVersion)

class SongPlayed(aggregateVersion: Int) : DomainEvent("song_played_v1", aggregateVersion)
