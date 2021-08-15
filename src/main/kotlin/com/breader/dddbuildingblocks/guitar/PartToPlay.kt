package com.breader.dddbuildingblocks.guitar

import com.breader.dddbuildingblocks.common.Specification

data class PartToPlay(val tuning: Tuning, val toneSpec: Specification<Guitar>)
