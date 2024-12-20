package com.br.taximeapi.domain.shared.exceptions

data class ResourceNotFoundException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
