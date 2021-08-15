package com.breader.dddbuildingblocks.guitar.specification

import com.breader.dddbuildingblocks.common.Specification
import com.breader.dddbuildingblocks.guitar.Guitar

val warmupToneSpecification = Specification<Guitar> { it.volumeKnob.level == 0 }