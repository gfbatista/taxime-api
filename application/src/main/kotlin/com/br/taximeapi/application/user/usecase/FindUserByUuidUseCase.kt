package com.br.taximeapi.application.user.usecase

import com.br.taximeapi.application.usecase.UseCase
import com.br.taximeapi.application.user.usecase.dto.FindUserByUuidUseCaseDto.Input
import com.br.taximeapi.application.user.usecase.dto.FindUserByUuidUseCaseDto.Output
import com.br.taximeapi.domain.shared.exceptions.ResourceNotFoundException
import com.br.taximeapi.domain.user.repositories.FindUserByUuidRepository
import kotlin.jvm.Throws

class FindUserByUuidUseCase(
    private val findUserByUuidRepository: FindUserByUuidRepository,
) : UseCase<Input, Output>() {
    @Throws(ResourceNotFoundException::class)
    override fun execute(input: Input): Output {
        val (uuid) = input

        val user =
            this.findUserByUuidRepository.byUuid(uuid) ?: throw ResourceNotFoundException("User of uuid $uuid not found.")

        return Output(
            uuid = user.uuid()!!,
            name = user.name(),
            email = user.email(),
        )
    }
}
