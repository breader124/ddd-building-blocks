package com.breader.dddbuildingblocks.common

public interface Specification<T> {
    fun isSatisfiedBy(target: T): Boolean
}
