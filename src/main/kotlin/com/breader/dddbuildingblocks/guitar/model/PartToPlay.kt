package com.breader.dddbuildingblocks.guitar.model

import com.breader.dddbuildingblocks.common.Specification
import com.breader.dddbuildingblocks.guitar.model.specification.ToneCheckResult

data class PartToPlay(val tuning: Tuning, val toneSpec: Specification<Guitar, ToneCheckResult>)
