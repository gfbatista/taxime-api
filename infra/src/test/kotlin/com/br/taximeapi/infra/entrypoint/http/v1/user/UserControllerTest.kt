package com.br.taximeapi.infra.entrypoint.http.v1.user

import com.br.taximeapi.application.user.usecase.CreateUserUseCase
import com.br.taximeapi.application.user.usecase.dto.CreateUserUseCaseDto
import com.br.taximeapi.infra.entrypoint.http.v1.user.dto.request.CreateUserRequest
import com.br.taximeapi.infra.shared.MockitoHelper
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
class UserControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var createUserUseCase: CreateUserUseCase

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private var userUuid = UUID.randomUUID()

    @Test
    fun `It should create a new user and return 201`() {
        val request = CreateUserRequest("User Name", "password", "email@teste.com")

        `when`(
            createUserUseCase.execute(MockitoHelper.anyObject()),
        ).thenReturn(CreateUserUseCaseDto.Output(this.userUuid, "User Name", "password", "email@teste.com"))

        mockMvc
            .perform(
                post("/v1/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)),
            ).andExpect(status().isCreated)
            .andExpect(header().string("Location", "/v1/users/$userUuid"))

        verify(createUserUseCase, times(1)).execute(MockitoHelper.anyObject())
    }
}
