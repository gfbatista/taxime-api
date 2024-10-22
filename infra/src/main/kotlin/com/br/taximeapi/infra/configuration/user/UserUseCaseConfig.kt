package com.br.taximeapi.infra.configuration.user

import com.br.taximeapi.application.user.usecase.CreateUserUseCase
import com.br.taximeapi.domain.user.repositories.CreateUserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserUseCaseConfig(
    private val createUserRepository: CreateUserRepository,
) {
    @Bean
    fun createUserUseCase(): CreateUserUseCase = CreateUserUseCase(createUserRepository)
}
