package com.breader.dddbuildingblocks.organizer.web

import com.breader.dddbuildingblocks.guitar.application.ManufacturingGuitar
import com.breader.dddbuildingblocks.guitar.application.PlayingSong
import com.breader.dddbuildingblocks.guitar.model.Guitar
import com.breader.dddbuildingblocks.guitar.model.GuitarId
import com.breader.dddbuildingblocks.guitar.model.Guitars
import com.breader.dddbuildingblocks.organizer.model.GuitarDTO
import com.breader.dddbuildingblocks.organizer.model.ManufactureGuitarRequest
import com.breader.dddbuildingblocks.organizer.model.PlaySongRequest
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController("guitar")
class GuitarController(
    private val manufacturingGuitar: ManufacturingGuitar,
    private val playingSong: PlayingSong,
    private val guitars: Guitars
) {

    @GetMapping("{id}")
    fun showGuitar(@PathVariable id: UUID): ResponseEntity<GuitarDTO> {
        val guitar = guitars.findById(GuitarId(id)) ?: throw NoSuchElementException("No such element with id=$id exists")
        val dto = GuitarDTO.fromDomainModel(guitar)
        dto.add(linkTo(methodOn(GuitarController::class.java).showGuitar(id)).withSelfRel())
        dto.add(linkTo(GuitarController::class.java).slash(id).withRel("play"))
        return ResponseEntity.ok(dto)
    }

    @PostMapping
    fun manufactureGuitar(@RequestBody request: ManufactureGuitarRequest): ResponseEntity<EntityModel<Void>> {
        val manufacturedGuitarId = manufacturingGuitar.handle(request.toCommand())
        val linkToShowGuitar = linkTo(methodOn(GuitarController::class.java).showGuitar(manufacturedGuitarId.id))
        val linkAsUri = linkToShowGuitar.toUri()
        return ResponseEntity.created(linkAsUri).build()
    }

    @PostMapping("{id}/play")
    fun playSong(@PathVariable guitarId: UUID, @RequestBody request: PlaySongRequest): ResponseEntity<EntityModel<Void>> {
        playingSong.handle(request.toCommand())
        return ResponseEntity.noContent().build()
    }

}