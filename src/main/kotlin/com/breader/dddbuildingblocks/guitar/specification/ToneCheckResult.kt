package com.breader.dddbuildingblocks.guitar.specification

import com.breader.dddbuildingblocks.guitar.Pickup

data class ToneCheckResult(
    val code: List<ToneCheckCode>,
    val desiredPickup: Pickup?,
    val desiredToneKnobLevel: Int?,
    val desiredVolKnobLevel: Int?
)
