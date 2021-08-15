package com.breader.dddbuildingblocks.guitar

import com.breader.dddbuildingblocks.common.Specification
import com.breader.dddbuildingblocks.guitar.specification.ToneCheckResult

data class PartToPlay(val tuning: Tuning, val toneSpec: Specification<Guitar, ToneCheckResult>)
