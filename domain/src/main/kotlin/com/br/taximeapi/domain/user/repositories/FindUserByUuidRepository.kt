package com.br.taximeapi.domain.user.repositories

import com.br.taximeapi.domain.user.User
import java.util.UUID

interface FindUserByUuidRepository {
    fun byUuid(uuid: UUID): User?
}
