package com.br.taximeapi.infra.database.repositories.user

import com.br.taximeapi.domain.user.User
import com.br.taximeapi.domain.user.repositories.CreateUserRepository
import com.br.taximeapi.infra.database.postgresql.entity.toEntity
import com.br.taximeapi.infra.database.postgresql.repository.UserPostgresRepository
import org.springframework.stereotype.Component

@Component
class CreateUserPostgresRepository(
    private val userPostgresRepository: UserPostgresRepository,
) : CreateUserRepository {
    override fun create(user: User): User {
        val userEntity = user.toEntity()
        return this.userPostgresRepository.save(userEntity).toDomain()
    }
}
