package com.br.taximeapi.infra.database.postgresql.entity

import com.br.taximeapi.domain.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(nullable = false, updatable = false)
    val uuid: UUID?,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val email: String,
    @Column(nullable = false)
    val password: String,
    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null,
) {
    fun toDomain(): User =
        User(
            uuid = uuid,
            name = name,
            email = email,
            password = password,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
        )
}

fun User.toEntity() =
    UserEntity(
        uuid = uuid(),
        name = name(),
        email = email(),
        password = password(),
        createdAt = createdAt(),
        updatedAt = updatedAt(),
        deletedAt = deletedAt(),
    )
