package com.br.taximeapi.infra.entrypoint.http.config.documentation

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.swagger.v3.core.jackson.ModelResolver
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig(
    @Value("\${application.name}")
    private val appName: String,
    @Value("\${application.version}")
    private val appVersion: String,
    @Value("\${application.description}")
    private val appDescription: String,
) {
    @Bean
    fun customOpenAPI(): OpenAPI =
        OpenAPI()
            .info(
                Info()
                    .title(appName)
                    .version(appVersion)
                    .description(appDescription),
            )

    @Bean
    fun modelResolver(objectMapper: ObjectMapper): ModelResolver =
        ModelResolver(objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE))
}
