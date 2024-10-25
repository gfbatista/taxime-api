package com.br.taximeapi.infra.shared

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
interface UnitTest {
    @Suppress("UNCHECKED_CAST")
    fun <T> uninitialized(): T = null as T
}
