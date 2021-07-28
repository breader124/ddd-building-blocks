package com.breader.dddbuildingblocks.guitar

class Note private constructor(val height: Int) {
    companion object {
        fun ofHeight(height: Int): Note {
            if (height !in 0..11) {
                throw IllegalArgumentException("Height was $height, it should be in <0, 11> range")
            }
            return Note(height)
        }
    }
}
