package com.breader.dddbuildingblocks.guitar.model.specification

import com.breader.dddbuildingblocks.common.Specification
import com.breader.dddbuildingblocks.guitar.model.Guitar

val warmupToneSpecification = Specification<Guitar, ToneCheckResult> {
    if (it.volumeKnob?.level == 0) {
        ToneCheckResult(listOf(ToneCheckCode.OK), null, null, null)
    } else {
        ToneCheckResult(listOf(ToneCheckCode.INVALID_VOL_KNOB_LEVEL), null, null, 0)
    }
}
