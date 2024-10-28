package com.br.taximeapi.application.user.usercase

import com.br.taximeapi.application.user.repository.InMemoryUserRepository
import com.br.taximeapi.application.user.usecase.FindUserByUuidUseCase
import com.br.taximeapi.application.user.usecase.dto.FindUserByUuidUseCaseDto.Input
import com.br.taximeapi.domain.shared.exceptions.ResourceNotFoundException
import com.br.taximeapi.domain.user.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class FindUseryUuidUseCaseTest {
    private lateinit var repository: InMemoryUserRepository

    @BeforeEach
    fun setUp() {
        repository = InMemoryUserRepository()
    }

    @Test
    fun `It should return user details when user with given uuid exists`() {
        val existingUser = repository.create(User.new("User Name", "contact@example.com", "User password"))

        val sut = FindUserByUuidUseCase(findUserByUuidRepository = repository)
        val input = Input(uuid = existingUser.uuid()!!)

        val output = sut.execute(input)

        Assertions.assertNotNull(output.uuid)

        Assertions.assertEquals(existingUser.uuid(), output.uuid)
        Assertions.assertEquals(existingUser.name(), output.name)
        Assertions.assertEquals(existingUser.email(), output.email)
    }

    @Test
    fun `It should throw ResourceNotFoundException when user with given uuid does not exist`() {
        val sut = FindUserByUuidUseCase(findUserByUuidRepository = repository)
        val nonExistentUuid = UUID.randomUUID()
        val input = Input(uuid = nonExistentUuid)

        val expectedError = "User of uuid $nonExistentUuid not found."

        val exception =
            assertThrows<ResourceNotFoundException> {
                sut.execute(input)
            }

        Assertions.assertEquals(expectedError, exception.message)
    }
}
