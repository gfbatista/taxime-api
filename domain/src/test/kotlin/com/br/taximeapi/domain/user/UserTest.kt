package com.br.taximeapi.domain.user

import com.br.taximeapi.domain.exceptions.InvalidStringValueException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class UserTest {
    @Test
    fun `It should create a valid User instance`() {
        val user =
            User(
                name = "User Name",
                password = "User Password",
                email = "contact@example.com",
                createdAt = LocalDateTime.now(),
                updatedAt = null,
                deletedAt = null,
            )

        Assertions.assertNotNull(user)
        Assertions.assertEquals("User Name", user.name())
        Assertions.assertNotEquals("User Password", user.password())
        Assertions.assertEquals("contact@example.com", user.email())
    }

    @Test
    fun `It should encrypt the email correctly`() {
        val user =
            User(
                name = "User Name",
                password = "User Password",
                email = "contact@example.com",
                createdAt = LocalDateTime.now(),
                updatedAt = null,
                deletedAt = null,
            )

        Assertions.assertEquals("8a521223e53701e77f81e4e2d16e7db1", user.password())
    }

    @Test
    fun `It should throw InvalidStringValueException when name is too short`() {
        val exception =
            assertThrows<InvalidStringValueException> {
                User(
                    name = "Us",
                    password = "User Password",
                    email = "contact@example.com",
                    createdAt = LocalDateTime.now(),
                    updatedAt = null,
                    deletedAt = null,
                )
            }

        Assertions.assertEquals("User name must be bigger or equal to 3.", exception.message)
    }

    @Test
    fun `It should throw InvalidStringValueException when email is invalid`() {
        val exception =
            assertThrows<InvalidStringValueException> {
                User(
                    name = "User Name",
                    password = "User Password",
                    email = "contact@example@.com",
                    createdAt = LocalDateTime.now(),
                    updatedAt = null,
                    deletedAt = null,
                )
            }

        Assertions.assertEquals("Invalid e-mail.", exception.message)
    }

    @Test
    fun `It should throw InvalidStringValueException when email is too short`() {
        val exception =
            assertThrows<InvalidStringValueException> {
                User(
                    name = "User Name",
                    password = "pass",
                    email = "contact@example.com",
                    createdAt = LocalDateTime.now(),
                    updatedAt = null,
                    deletedAt = null,
                )
            }

        Assertions.assertEquals("User password info must be bigger or equal to 8.", exception.message)
    }

    @Test
    fun `It should create a valid User using the companion object`() {
        val user =
            User.new(
                name = "User Name",
                password = "User Password",
                email = "contact@example.com",
            )

        Assertions.assertNotNull(user)
        Assertions.assertEquals("User Name", user.name())
        Assertions.assertEquals("8a521223e53701e77f81e4e2d16e7db1", user.password())
        Assertions.assertEquals("contact@example.com", user.email())
        Assertions.assertNotNull(user.createdAt())
        Assertions.assertNull(user.updatedAt())
        Assertions.assertNull(user.deletedAt())
    }
}
