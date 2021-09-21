package com.breader.dddbuildingblocks.organizer.web

import com.breader.dddbuildingblocks.organizer.model.ManufactureGuitarRequest
import com.breader.dddbuildingblocks.organizer.model.PlaySongRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController("guitar")
class GuitarController {

    @PostMapping
    fun manufactureGuitar(@RequestBody request: ManufactureGuitarRequest): ResponseEntity<Void> {
        TODO("Not implemented yet")
    }

    @PutMapping("{id}/play")
    fun playSong(@PathVariable guitarId: UUID, @RequestBody request: PlaySongRequest): ResponseEntity<Void> {
        TODO("Not implemented yet")
    }

}