package com.br.taximeapi.infra.entrypoint.http.v1.user

import com.br.taximeapi.application.user.usecase.CreateUserUseCase
import com.br.taximeapi.application.user.usecase.FindUserByUuidUseCase
import com.br.taximeapi.application.user.usecase.dto.CreateUserUseCaseDto
import com.br.taximeapi.application.user.usecase.dto.FindUserByUuidUseCaseDto
import com.br.taximeapi.domain.shared.exceptions.ResourceNotFoundException
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
class UserControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var createUserUseCase: CreateUserUseCase

    @MockBean
    private lateinit var findUserByUuidUseCase: FindUserByUuidUseCase

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private var userUuid = UUID.randomUUID()

    private var userRequest = CreateUserRequest("User Name", "password", "email@teste.com")

    @Test
    fun `It should create a new user and return 201`() {
        `when`(
            createUserUseCase.execute(MockitoHelper.anyObject()),
        ).thenReturn(CreateUserUseCaseDto.Output(this.userUuid, "User Name", "password", "email@teste.com"))

        mockMvc
            .perform(
                post("/v1/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(this.userRequest)),
            ).andExpect(status().isCreated)
            .andExpect(header().string("Location", "/v1/users/$userUuid"))

        verify(createUserUseCase, times(1)).execute(MockitoHelper.anyObject())
    }

    @Test
    fun `It should return a user and return 200`() {
        `when`(
            createUserUseCase.execute(MockitoHelper.anyObject()),
        ).thenReturn(CreateUserUseCaseDto.Output(this.userUuid, "User Name", "password", "email@teste.com"))

        mockMvc
            .perform(
                post("/v1/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(this.userRequest)),
            ).andExpect(status().isCreated)
            .andExpect(header().string("Location", "/v1/users/$userUuid"))

        verify(createUserUseCase, times(1)).execute(MockitoHelper.anyObject())
    }

    @Test
    fun `It should find a user by uuid and return 200`() {
        val userResponse = FindUserByUuidUseCaseDto.Output(this.userUuid, "User Name", "email@teste.com")

        `when`(findUserByUuidUseCase.execute(MockitoHelper.anyObject())).thenReturn(userResponse)

        mockMvc
            .perform(
                get("/v1/users/{id}", userResponse.uuid)
                    .contentType(MediaType.APPLICATION_JSON),
            ).andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(userResponse.name))
            .andExpect(jsonPath("$.email").value(userResponse.email))

        verify(findUserByUuidUseCase, times(1)).execute(MockitoHelper.anyObject())
    }

    @Test
    fun `It should return 404 when user not found by id`() {
        `when`(findUserByUuidUseCase.execute(MockitoHelper.anyObject())).thenThrow(ResourceNotFoundException("User not found."))

        mockMvc
            .perform(
                get("/v1/users/{id}", this.userUuid)
                    .contentType(MediaType.APPLICATION_JSON),
            ).andExpect(status().isNotFound)

        verify(findUserByUuidUseCase, times(1)).execute(MockitoHelper.anyObject())
    }
}
