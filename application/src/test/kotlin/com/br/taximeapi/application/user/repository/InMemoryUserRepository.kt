package com.br.taximeapi.application.user.repository

import com.br.taximeapi.domain.user.User
import com.br.taximeapi.domain.user.repositories.CreateUserRepository

class InMemoryUserRepository : CreateUserRepository {
    private val users: MutableSet<User> = mutableSetOf()

    override fun create(user: User): User {
        users.add(user)
        return user
    }
}
