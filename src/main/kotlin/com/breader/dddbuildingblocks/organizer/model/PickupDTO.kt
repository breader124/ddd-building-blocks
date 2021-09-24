package com.breader.dddbuildingblocks.organizer.model

import org.springframework.hateoas.RepresentationModel

data class PickupDTO(
    val type: String,
    val pos: String
) : RepresentationModel<PickupDTO>()