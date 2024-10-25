package com.br.taximeapi.application.user.usercase

import com.br.taximeapi.application.user.repository.InMemoryUserRepository
import com.br.taximeapi.application.user.usecase.CreateUserUseCase
import com.br.taximeapi.application.user.usecase.dto.CreateUserUseCaseDto.Input
import com.br.taximeapi.domain.shared.hash
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CreateUserUseCaseTest {
    @Test
    fun `It should create a user successfully`() {
        val repository = InMemoryUserRepository()
        val sut = CreateUserUseCase(createUserRepository = repository)

        val expectedName = "User Name"
        val expectedPassword = "User Password"
        val expectedEmail = "contact@example.com"
        val input =
            Input(
                name = expectedName,
                password = expectedPassword,
                email = expectedEmail,
            )

        val output = sut.execute(input)

        Assertions.assertNotNull(output.uuid)

        Assertions.assertEquals(expectedName, input.name)
        Assertions.assertEquals(expectedPassword, input.password)
        Assertions.assertEquals(expectedEmail, input.email)

        Assertions.assertEquals(expectedName, output.name)
        Assertions.assertEquals(expectedPassword.hash(), output.password)
        Assertions.assertEquals(expectedEmail, output.email)
    }
}
