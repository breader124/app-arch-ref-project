package com.breader.hexagonal.adapter

import com.breader.hexagonal.domain.Transfer
import com.breader.hexagonal.usecase.port.TransferRepo

class InMemoryTransferRepo : TransferRepo {

    private val db: MutableList<Transfer> = mutableListOf()

    override fun save(t: Transfer): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(t: Transfer): Boolean {
        TODO("Not yet implemented")
    }

}
