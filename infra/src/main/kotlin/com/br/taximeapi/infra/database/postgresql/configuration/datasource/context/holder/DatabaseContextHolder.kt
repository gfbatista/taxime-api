package com.br.taximeapi.infra.database.postgresql.configuration.datasource.context.holder

import com.br.taximeapi.infra.database.postgresql.configuration.datasource.context.DatabaseEnvironment
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class DatabaseContextHolder : AbstractRoutingDataSource() {
    companion object {
        private val CONTEXT = ThreadLocal<DatabaseEnvironment>()

        fun set(databaseEnvironment: DatabaseEnvironment) {
            CONTEXT.set(databaseEnvironment)
        }

        fun get(): DatabaseEnvironment = CONTEXT.get()

        fun reset() {
            CONTEXT.set(DatabaseEnvironment.PRIMARY)
        }
    }

    override fun determineCurrentLookupKey(): DatabaseEnvironment = CONTEXT.get() ?: DatabaseEnvironment.PRIMARY
}
