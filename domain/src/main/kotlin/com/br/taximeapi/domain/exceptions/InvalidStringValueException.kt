package com.br.taximeapi.domain.exceptions

data class InvalidStringValueException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
