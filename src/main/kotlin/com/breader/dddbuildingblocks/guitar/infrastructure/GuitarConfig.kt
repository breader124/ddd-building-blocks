package com.breader.dddbuildingblocks.guitar.infrastructure

import com.breader.dddbuildingblocks.common.event.publishing.domain.EventPublisher
import com.breader.dddbuildingblocks.common.event.storage.domain.StorageClient
import com.breader.dddbuildingblocks.common.event.storage.infrastructure.EventMapper
import com.breader.dddbuildingblocks.guitar.application.ManufacturingGuitar
import com.breader.dddbuildingblocks.guitar.application.PlayingSong
import com.breader.dddbuildingblocks.guitar.model.Guitars
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GuitarConfig(
    private val storageClient: StorageClient,
    private val eventPublisher: EventPublisher,
    private val eventMapper: EventMapper
) {

    @Bean
    fun guitars(): Guitars = EventStoreGuitarRepository(storageClient, eventMapper)

    @Bean
    fun manufacturingGuitar(guitars: Guitars): ManufacturingGuitar = ManufacturingGuitar(eventPublisher)

    @Bean
    fun playingSong(guitars: Guitars, eventPublisher: EventPublisher): PlayingSong = PlayingSong(
        guitars,
        eventPublisher
    )

}
