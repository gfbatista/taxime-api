package com.br.taximeapi.infra.configuration.user

import com.br.taximeapi.application.user.usecase.CreateUserUseCase
import com.br.taximeapi.application.user.usecase.FindUserByUuidUseCase
import com.br.taximeapi.domain.user.repositories.CreateUserRepository
import com.br.taximeapi.domain.user.repositories.FindUserByUuidRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserUseCaseConfig(
    private val createUserRepository: CreateUserRepository,
    private val findUserByUuidRepository: FindUserByUuidRepository,
) {
    @Bean
    fun createUserUseCase(): CreateUserUseCase = CreateUserUseCase(createUserRepository)

    @Bean
    fun findUserByUuidUseCase(): FindUserByUuidUseCase = FindUserByUuidUseCase(findUserByUuidRepository)
}
