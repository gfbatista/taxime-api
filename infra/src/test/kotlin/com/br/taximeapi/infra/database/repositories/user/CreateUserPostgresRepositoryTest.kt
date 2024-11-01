package com.br.taximeapi.infra.database.repositories.user

import com.br.taximeapi.domain.user.User
import com.br.taximeapi.infra.database.postgresql.entity.factory.UserEntityFactory
import com.br.taximeapi.infra.database.postgresql.repository.UserPostgresRepository
import com.br.taximeapi.infra.shared.UnitTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class CreateUserPostgresRepositoryTest : UnitTest {
    @Mock
    private lateinit var userPostgresRepository: UserPostgresRepository

    @InjectMocks
    private lateinit var sut: CreateUserPostgresRepository

    @BeforeEach
    fun setUp() {
        sut = CreateUserPostgresRepository(userPostgresRepository)
    }

    @Test
    fun `It should create a user and return the created user`() {
        val expectedName = "User Name"
        val expectedPassword = "password"
        val expectedEmail = "contact@example.com"

        val user = User.new(expectedName, expectedEmail, expectedPassword)
        val userEntity =
            UserEntityFactory.build(name = expectedName, password = expectedPassword, email = expectedEmail)

        Mockito.`when`(this.userPostgresRepository.save(Mockito.any())).thenReturn(userEntity)

        val result = sut.create(user)

        Assertions.assertNotNull(result)
        Mockito.verify(userPostgresRepository, Mockito.times(1)).save(Mockito.any())
    }
}
