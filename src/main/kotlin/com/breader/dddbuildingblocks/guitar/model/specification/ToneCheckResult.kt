package com.breader.dddbuildingblocks.guitar.model.specification

import com.breader.dddbuildingblocks.guitar.model.Pickup

data class ToneCheckResult(
    val codes: List<ToneCheckCode>,
    val desiredPickup: Pickup?,
    val desiredToneKnobLevel: Int?,
    val desiredVolKnobLevel: Int?
)
