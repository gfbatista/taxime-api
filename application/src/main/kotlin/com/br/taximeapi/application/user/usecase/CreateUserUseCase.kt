package com.br.com.br.taximeapi.application.user.usecase

import com.br.com.br.taximeapi.application.usecase.UseCase

class CreateUserUseCase : UseCase<CreateUserUseCase.Input, CreateUserUseCase.Output>() {
    override fun execute(input: Input): Output {
        TODO("Not yet implemented")
    }

    data class Input(
        val name: String,
    )

    data class Output(
        val id: String,
        val name: String,
    )
}
