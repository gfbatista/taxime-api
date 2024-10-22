package com.br.taximeapi.infra.entrypoint.http.v1.user.dto.request

import com.br.taximeapi.application.user.usecase.dto.CreateUserUseCaseDto.Input
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateUserRequest(
    @field:NotBlank(message = "User name must not be blank.")
    @field:Size(min = 3, message = "User name must be bigger or equal to 3.")
    val name: String,
    @field:NotBlank(message = "User password must not be blank.")
    @field:Size(min = 8, message = "User password must be bigger or equal to 8.")
    val password: String,
    @field:NotBlank(message = "User e-mail must not be blank.")
    val email: String,
) {
    fun toInput() =
        Input(
            name = name,
            password = password,
            email = email,
        )
}
