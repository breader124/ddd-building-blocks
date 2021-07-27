package com.breader.dddbuildingblocks.guitar

class Note private constructor(val height: Int) {

    fun ofHeight(height: Int): Note {
        return Note(
            if (height in 0..11) {
                height
            } else {
                throw IllegalArgumentException("Height was $height, it should be in <0, 11> range")
            }
        )
    }

}
