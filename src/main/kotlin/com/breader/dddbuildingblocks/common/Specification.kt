package com.breader.dddbuildingblocks.common

fun interface Specification<T> {
    fun isSatisfiedBy(target: T): Boolean
}
