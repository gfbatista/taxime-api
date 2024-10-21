package com.br.taximeapi.infra.database.postgresql.repository

import com.br.taximeapi.infra.database.postgresql.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserPostgresRepository : JpaRepository<UserEntity, Int>
