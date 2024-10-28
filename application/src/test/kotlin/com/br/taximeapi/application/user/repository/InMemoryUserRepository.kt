package com.br.taximeapi.application.user.repository

import com.br.taximeapi.domain.user.User
import com.br.taximeapi.domain.user.repositories.CreateUserRepository
import com.br.taximeapi.domain.user.repositories.FindUserByUuidRepository
import java.util.UUID

class InMemoryUserRepository :
    CreateUserRepository,
    FindUserByUuidRepository {
    private val users: MutableSet<User> = mutableSetOf()

    override fun create(user: User): User {
        users.add(user)
        return user
    }

    override fun byUuid(uuid: UUID): User? = users.find { it.uuid() == uuid }
}
