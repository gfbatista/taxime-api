package com.br.taximeapi.infra.database.postgresql.configuration.datasource

import com.br.taximeapi.infra.database.postgresql.configuration.datasource.context.DatabaseEnvironment
import com.br.taximeapi.infra.database.postgresql.configuration.datasource.context.holder.DatabaseContextHolder
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.env.Environment
import javax.sql.DataSource

@Configuration
class DataSourceConfig(
    @Value("\${application.env:unknown}")
    private val applicationEnvironment: String,
    private val environment: Environment,
) {
    companion object {
        const val PRIMARY_DATASOURCE_PREFIX = "spring.datasource.primary"
    }

    @Bean
    @Primary
    fun config(): DataSource {
        val targetDataSources = HashMap<Any, Any>()

        val primaryHikariConfig = this.buildHikariConfig("PrimaryHikariPool", PRIMARY_DATASOURCE_PREFIX)
        val primaryDataSource = HikariDataSource(primaryHikariConfig)

        targetDataSources[DatabaseEnvironment.PRIMARY] = primaryDataSource

        val databaseContext = DatabaseContextHolder()

        databaseContext.setTargetDataSources(targetDataSources)
        databaseContext.setDefaultTargetDataSource(primaryDataSource)

        return databaseContext
    }

    private fun buildHikariConfig(
        poolName: String,
        dataSourcePrefix: String,
    ): HikariConfig {
        val hikariConfig = HikariConfig()

        hikariConfig.poolName = poolName
        hikariConfig.jdbcUrl = this.environment.getProperty(String.format("%s.url", dataSourcePrefix))
        hikariConfig.username = this.environment.getProperty(String.format("%s.username", dataSourcePrefix))
        hikariConfig.password = this.environment.getProperty(String.format("%s.password", dataSourcePrefix))

        if (this.applicationEnvironment != "test") {
            this.configureHikariConfigOptions(hikariConfig, dataSourcePrefix)
        }

        return hikariConfig
    }

    private fun configureHikariConfigOptions(
        hikariConfig: HikariConfig,
        dataSourcePrefix: String,
    ): HikariConfig {
        hikariConfig.schema = this.environment.getProperty(String.format("%s.hikari.schema", dataSourcePrefix))
        hikariConfig.minimumIdle =
            (this.environment.getProperty(String.format("%s.hikari.minimumIdle", dataSourcePrefix)) ?: "5").toInt()
        hikariConfig.maximumPoolSize =
            (this.environment.getProperty(String.format("%s.hikari.maximumPoolSize", dataSourcePrefix)) ?: "25").toInt()

        return hikariConfig
    }
}
