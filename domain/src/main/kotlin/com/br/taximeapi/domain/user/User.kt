package com.br.taximeapi.domain.user

import com.br.taximeapi.domain.exceptions.InvalidStringValueException
import com.br.taximeapi.domain.shared.hash
import java.time.LocalDateTime
import java.util.UUID

data class User(
    private var uuid: UUID? = null,
    private val name: String,
    private val email: String,
    private var password: String,
    private val createdAt: LocalDateTime,
    private val updatedAt: LocalDateTime?,
    private val deletedAt: LocalDateTime?,
) {
    fun uuid(): UUID? = uuid

    fun name() = name

    fun email() = email

    fun password() = password

    fun createdAt() = createdAt

    fun updatedAt() = updatedAt

    fun deletedAt() = deletedAt

    init {
        if (uuid() == null) {
            this.uuid = UUID.randomUUID()
        }

        if (name().length <= 3) throw InvalidStringValueException("User name must be bigger or equal to 3.")

        if (password().length < 8) throw InvalidStringValueException("User password info must be bigger or equal to 8.")
        this.password = password().hash()

        val emailRegex = "^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?\$".toRegex()
        if (!email().matches(emailRegex)) {
            throw InvalidStringValueException("Invalid e-mail.")
        }
    }

    companion object {
        fun new(
            name: String,
            email: String,
            password: String,
        ) = User(
            name = name,
            email = email,
            password = password,
            createdAt = LocalDateTime.now(),
            updatedAt = null,
            deletedAt = null,
        )
    }
}
