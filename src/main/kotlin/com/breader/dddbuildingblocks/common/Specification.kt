package com.breader.dddbuildingblocks.common

fun interface Specification<T, R> {
    fun isSatisfiedBy(target: T): R
}
