package com.br.taximeapi.infra.database.postgresql.entity

import com.br.taximeapi.domain.shared.hash
import com.br.taximeapi.domain.user.User
import com.br.taximeapi.infra.shared.UnitTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.UUID

class UserEntityFactoryTest : UnitTest {
    @Test
    fun `toDomain should convert UserEntity to User`() {
        val now = LocalDateTime.now()
        val uuid = UUID.randomUUID()
        val userEntity =
            UserEntity(
                uuid = uuid,
                name = "User Name",
                password = "User Password",
                email = "user@email.com",
                createdAt = now,
                updatedAt = null,
                deletedAt = null,
            )

        val userDomain = userEntity.toDomain()

        Assertions.assertEquals(uuid, userDomain.uuid())
        Assertions.assertEquals("User Name", userDomain.name())
        Assertions.assertEquals("User Password".hash(), userDomain.password())
        Assertions.assertEquals("user@email.com", userDomain.email())
        Assertions.assertEquals(now, userDomain.createdAt())
        Assertions.assertEquals(null, userDomain.updatedAt())
        Assertions.assertEquals(null, userDomain.deletedAt())
    }

    @Test
    fun `toEntity should convert User to UserEntity`() {
        val user = User.new("User Name", "user@email.com", "User Password")

        val userEntity = user.toEntity()

        Assertions.assertEquals(user.name(), userEntity.name)
        Assertions.assertEquals(user.password(), userEntity.password)
        Assertions.assertEquals(user.email(), userEntity.email)
        Assertions.assertEquals(user.createdAt(), userEntity.createdAt)
        Assertions.assertEquals(user.updatedAt(), userEntity.updatedAt)
        Assertions.assertEquals(user.deletedAt(), userEntity.deletedAt)
    }
}
