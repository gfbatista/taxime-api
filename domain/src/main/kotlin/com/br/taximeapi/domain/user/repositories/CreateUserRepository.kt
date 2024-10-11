package com.br.taximeapi.domain.user.repositories

import com.br.taximeapi.domain.user.User

interface CreateUserRepository {
    fun create(user: User): User
}
