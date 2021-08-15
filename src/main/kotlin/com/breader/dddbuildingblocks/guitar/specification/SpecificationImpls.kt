package com.breader.dddbuildingblocks.guitar.specification

import com.breader.dddbuildingblocks.common.Specification
import com.breader.dddbuildingblocks.guitar.Guitar

val warmupToneSpecification = Specification<Guitar, ToneCheckResult> {
    if (it.volumeKnob.level == 0) {
        ToneCheckResult(ToneCheckCode.OK, null, null, null)
    } else {
        ToneCheckResult(ToneCheckCode.INVALID_VOL_KNOB_LEVEL, null, null, 0)
    }
}