package com.br.taximeapi.application.user.usecase

import com.br.taximeapi.application.usecase.UseCase
import com.br.taximeapi.application.user.usecase.dto.CreateUserUseCaseDto.Input
import com.br.taximeapi.application.user.usecase.dto.CreateUserUseCaseDto.Output
import com.br.taximeapi.domain.user.User
import com.br.taximeapi.domain.user.repositories.CreateUserRepository

class CreateUserUseCase(
    private val createUserRepository: CreateUserRepository,
) : UseCase<Input, Output>() {
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
}
