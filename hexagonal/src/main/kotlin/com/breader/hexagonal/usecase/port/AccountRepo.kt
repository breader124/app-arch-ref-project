package com.breader.hexagonal.usecase.port

import com.breader.hexagonal.domain.AccountNumber

interface AccountRepo {
    fun findByNumber(number: AccountNumber)
}
