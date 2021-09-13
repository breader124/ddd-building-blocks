package com.breader.dddbuildingblocks.guitar.infrastructure

import com.breader.dddbuildingblocks.guitar.application.ManufacturingGuitar
import com.breader.dddbuildingblocks.guitar.application.PlayingSong
import com.breader.dddbuildingblocks.guitar.model.Guitars
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GuitarConfig {

    @Bean
    fun guitars(): Guitars = InMemoryGuitarRepository()

    @Bean
    fun manufacturingGuitar(): ManufacturingGuitar = ManufacturingGuitar(guitars())

    @Bean
    fun playingSong(): PlayingSong = PlayingSong(guitars())

}
