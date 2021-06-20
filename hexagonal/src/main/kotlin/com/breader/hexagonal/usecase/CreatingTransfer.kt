package com.breader.hexagonal.usecase

import com.breader.hexagonal.domain.Transfer
import com.breader.hexagonal.domain.TransferId
import com.breader.hexagonal.usecase.port.TransferRepo
import java.util.*

class CreatingTransfer(private val repo: TransferRepo) {

    fun createTransfer() {
        val t = Transfer(TransferId(UUID.randomUUID()))
        repo.save(t)
    }

}
