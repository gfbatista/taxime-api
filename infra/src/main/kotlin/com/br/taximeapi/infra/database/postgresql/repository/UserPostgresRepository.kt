package com.br.taximeapi.infra.database.postgresql.repository

import com.br.taximeapi.infra.database.postgresql.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface UserPostgresRepository : JpaRepository<UserEntity, Int> {
    fun findByUuidAndDeletedAtIsNull(uuid: UUID): Optional<UserEntity>
}
