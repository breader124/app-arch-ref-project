package com.breader.hexagonal.usecase.port

import com.breader.hexagonal.domain.User
import com.breader.hexagonal.domain.UserId

interface UserRepo {
    fun findById(userId: UserId): User
}
