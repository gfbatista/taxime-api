package com.br.taximeapi.infra.database.repositories.user

import com.br.taximeapi.domain.user.User
import com.br.taximeapi.domain.user.repositories.FindUserByUuidRepository
import com.br.taximeapi.infra.database.postgresql.repository.UserPostgresRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class FindUserByUuidPostgresRepository(
    private val userPostgresRepository: UserPostgresRepository,
) : FindUserByUuidRepository {
    override fun byUuid(uuid: UUID): User? {
        val userEntity = this.userPostgresRepository.findByUuidAndDeletedAtIsNull(uuid)
        return userEntity.map { it.toDomain() }.orElse(null)
    }
}
