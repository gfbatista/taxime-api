package com.br.taximeapi.application.user.usecase

import com.br.com.br.taximeapi.application.usecase.UseCase
import com.br.taximeapi.domain.user.User
import com.br.taximeapi.domain.user.repositories.CreateUserRepository
import java.util.UUID

class CreateUserUseCase(
    private val createUserRepository: CreateUserRepository,
) : UseCase<CreateUserUseCase.Input, CreateUserUseCase.Output>() {
    override fun execute(input: Input): Output {
        val (name, email, password) = input

        val carrier = User.new(name, email, password)
        val savedUser = this.createUserRepository.create(carrier)

        return Output(
            uuid = savedUser.uuid()!!,
            name = savedUser.name(),
            email = savedUser.email(),
            password = savedUser.password(),
        )
    }

    data class Input(
        val name: String,
        val email: String,
        val password: String,
    )

    data class Output(
        val uuid: UUID,
        val name: String,
        val email: String,
        val password: String,
    )
}
