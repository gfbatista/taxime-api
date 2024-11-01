package com.br.taximeapi.infra.entrypoint.http.config.exception.entity

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BaseResponseErrorMessage(
    val slug: String,
    val message: String,
    val details: List<DetailResponseErrorMessage>? = null,
)

data class DetailResponseErrorMessage(
    val field: String,
    val message: String? = null,
)
