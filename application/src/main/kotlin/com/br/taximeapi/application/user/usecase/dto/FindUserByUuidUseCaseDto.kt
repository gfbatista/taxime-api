package com.br.taximeapi.application.user.usecase.dto

import java.util.UUID

class FindUserByUuidUseCaseDto {
    data class Input(
        val uuid: UUID,
    )

    data class Output(
        val uuid: UUID,
        val name: String,
        val email: String,
    )
}
