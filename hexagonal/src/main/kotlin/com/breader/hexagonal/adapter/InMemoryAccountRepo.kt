package com.breader.hexagonal.adapter

import com.breader.hexagonal.domain.AccountNumber
import com.breader.hexagonal.usecase.port.AccountRepo

class InMemoryAccountRepo : AccountRepo {

    override fun findByNumber(number: AccountNumber) {
        TODO("Not yet implemented")
    }

}
