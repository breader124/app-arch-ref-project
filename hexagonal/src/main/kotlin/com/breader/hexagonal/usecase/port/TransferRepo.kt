package com.breader.hexagonal.usecase.port

import com.breader.hexagonal.domain.Transfer

interface TransferRepo {
    fun save(t: Transfer): Boolean
    fun modify(t: Transfer): Boolean
    fun delete(t: Transfer): Boolean
}
