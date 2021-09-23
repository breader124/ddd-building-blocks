package com.breader.dddbuildingblocks.organizer.web

import com.breader.dddbuildingblocks.guitar.application.ManufacturingGuitar
import com.breader.dddbuildingblocks.guitar.application.PlayingSong
import com.breader.dddbuildingblocks.guitar.model.Guitar
import com.breader.dddbuildingblocks.organizer.model.ManufactureGuitarRequest
import com.breader.dddbuildingblocks.organizer.model.PlaySongRequest
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController("guitar")
class GuitarController(
    private val manufacturingGuitar: ManufacturingGuitar,
    private val playingSong: PlayingSong
) {

    @GetMapping("{id}")
    fun showGuitar(@PathVariable id: UUID) {
        TODO("Not yet implemented")
    }

    @PostMapping
    fun manufactureGuitar(@RequestBody request: ManufactureGuitarRequest): ResponseEntity<EntityModel<Void>> {
        val manufacturedGuitarId = manufacturingGuitar.handle(request.toCommand())
        val linkToShowGuitar = linkTo(methodOn(GuitarController::class.java).showGuitar(manufacturedGuitarId.id))
        val linkAsUri = linkToShowGuitar.toUri()
        return ResponseEntity.created(linkAsUri).build()
    }

    @PutMapping("{id}/play")
    fun playSong(@PathVariable guitarId: UUID, @RequestBody request: PlaySongRequest): ResponseEntity<EntityModel<Void>> {
        TODO("Not yet implemented")
    }

}