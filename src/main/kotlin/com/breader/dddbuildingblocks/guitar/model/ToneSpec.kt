package com.breader.dddbuildingblocks.guitar.model

import com.breader.dddbuildingblocks.common.Specification
import com.breader.dddbuildingblocks.guitar.model.specification.ToneCheckCode
import com.breader.dddbuildingblocks.guitar.model.specification.ToneCheckResult

class ToneSpec private constructor(
    private val pickup: Pickup?,
    private val volumeLevel: Int?,
    private val toneLevel: Int?) : Specification<Guitar, ToneCheckResult> {

    override fun isSatisfiedBy(target: Guitar): ToneCheckResult {
        val toneCheckCodeList = mutableListOf<ToneCheckCode>()
        if (target.volumeKnob.level != volumeLevel) {
            toneCheckCodeList.add(ToneCheckCode.INVALID_VOL_KNOB_LEVEL)
        }
        if (target.toneKnob.level != toneLevel) {
            toneCheckCodeList.add(ToneCheckCode.INVALID_TONE_KNOB_LEVEL)
        }
        if (target.pickups.chosen != pickup) {
            toneCheckCodeList.add(ToneCheckCode.WRONG_PICKUP_CHOSEN)
        }

        if (toneCheckCodeList.isEmpty()) {
            toneCheckCodeList.add(ToneCheckCode.OK)
            return ToneCheckResult(toneCheckCodeList, null, null, null)
        }

        return ToneCheckResult(
            toneCheckCodeList,
            if (toneCheckCodeList.contains(ToneCheckCode.WRONG_PICKUP_CHOSEN)) target.pickups.chosen else null,
            if (toneCheckCodeList.contains(ToneCheckCode.INVALID_VOL_KNOB_LEVEL)) target.volumeKnob.level else null,
            if (toneCheckCodeList.contains(ToneCheckCode.INVALID_TONE_KNOB_LEVEL)) target.toneKnob.level else null,
        )
    }

    data class Builder(
        private var pickup: Pickup? = null,
        private var volumeLevel: Int? = null,
        private var toneLevel: Int? = null) {

        fun pickup(p: Pickup) = apply { this.pickup = p }
        fun volumeLevel(level: Int) = apply { this.volumeLevel = level }
        fun toneLevel(level: Int) = apply { this.toneLevel = level }
        fun build(): ToneSpec = ToneSpec(pickup, volumeLevel, toneLevel)
    }

}
