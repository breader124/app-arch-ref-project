package com.breader.hexagonal.adapter

import com.breader.hexagonal.domain.User
import com.breader.hexagonal.domain.UserId
import com.breader.hexagonal.usecase.port.UserRepo

class InMemoryUserRepo : UserRepo {

    override fun findById(userId: UserId): User {
        TODO("Not yet implemented")
    }

    override fun findByAccountNumber(accountNumber: String): User {
        TODO("Not yet implemented")
    }

}
