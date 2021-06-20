package com.breader.hexagonal.config

import com.breader.hexagonal.adapter.InMemoryTransferRepo
import com.breader.hexagonal.usecase.CreatingTransfer
import com.breader.hexagonal.usecase.port.TransferRepo
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfiguration {

    @Bean
    fun repo(): TransferRepo = InMemoryTransferRepo()

    @Bean
    fun creatingTransfer(): CreatingTransfer = CreatingTransfer(repo())
}
