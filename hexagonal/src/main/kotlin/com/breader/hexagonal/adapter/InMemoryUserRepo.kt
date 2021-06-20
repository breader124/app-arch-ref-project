package com.breader.hexagonal.adapter

import com.breader.hexagonal.domain.User
import com.breader.hexagonal.domain.UserId
import com.breader.hexagonal.usecase.port.UserRepo

class InMemoryUserRepo : UserRepo {

    private val db: MutableList<User> = mutableListOf()

    override fun findById(userId: UserId): User? = db.find { it.id == userId }

    override fun findByAccountNumber(accountNumber: String): User? = db.find { user ->
        user.accountList.map { it.accountNumber }.contains(accountNumber)
    }

    override fun save(user: User) {
        db.add(user)
    }
}
