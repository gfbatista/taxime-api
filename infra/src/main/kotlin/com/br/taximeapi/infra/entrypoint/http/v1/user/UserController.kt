package com.br.taximeapi.infra.entrypoint.http.v1.user

import com.br.taximeapi.application.user.usecase.CreateUserUseCase
import com.br.taximeapi.infra.entrypoint.http.v1.user.UserController.Companion.ENDPOINT_PATH
import com.br.taximeapi.infra.entrypoint.http.v1.user.dto.request.CreateUserRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping(ENDPOINT_PATH)
@Tag(name = "User")
class UserController(
    private val createUserUseCase: CreateUserUseCase,
) {
    companion object {
        const val ENDPOINT_PATH = "/v1/users"
    }

    @PostMapping
    @Operation(summary = "Create User")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "User created successfully",
            ),
        ],
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(
        @RequestBody @Valid request: CreateUserRequest,
    ): ResponseEntity<Void> {
        val (id) = this.createUserUseCase.execute(request.toInput())

        return ResponseEntity.created(URI.create("$ENDPOINT_PATH/$id")).build()
    }
}
