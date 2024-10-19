package com.br.taximeapi.application.user.usecase.dto

import java.util.UUID

class CreateUserUseCaseDto {
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
