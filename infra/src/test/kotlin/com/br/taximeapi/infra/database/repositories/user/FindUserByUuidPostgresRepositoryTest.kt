package com.br.taximeapi.infra.database.repositories.user

import com.br.taximeapi.infra.database.postgresql.entity.factory.UserEntityFactory
import com.br.taximeapi.infra.database.postgresql.repository.UserPostgresRepository
import com.br.taximeapi.infra.shared.UnitTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.util.Optional
import java.util.UUID

class FindUserByUuidPostgresRepositoryTest : UnitTest {
    @Mock
    private lateinit var userPostgresRepository: UserPostgresRepository

    @InjectMocks
    private lateinit var sut: FindUserByUuidPostgresRepository

    private val userUuid = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        sut = FindUserByUuidPostgresRepository(userPostgresRepository)
    }

    @Test
    fun `It should return a user when the ID exists`() {
        val expectedName = "User Name"
        val expectedPassword = "password"
        val expectedEmail = "contact@example.com"

        val userEntity =
            UserEntityFactory.build(uuid = this.userUuid, name = expectedName, password = expectedPassword, email = expectedEmail)

        Mockito.`when`(this.userPostgresRepository.findByUuidAndDeletedAtIsNull(this.userUuid)).thenReturn(Optional.of(userEntity))

        val result = sut.byUuid(this.userUuid)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(result!!.uuid(), this.userUuid)

        Mockito.verify(userPostgresRepository, Mockito.times(1)).findByUuidAndDeletedAtIsNull(this.userUuid)
    }

    @Test
    fun `it should be able to return null when the user is not found by uuid`() {
        Mockito.`when`(this.userPostgresRepository.findByUuidAndDeletedAtIsNull(this.userUuid)).thenReturn(Optional.empty())

        val result = this.sut.byUuid(this.userUuid)

        Assertions.assertNull(result)
    }
}
