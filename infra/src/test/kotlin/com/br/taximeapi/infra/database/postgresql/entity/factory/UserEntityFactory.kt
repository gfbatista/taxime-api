package com.br.taximeapi.infra.database.postgresql.entity.factory

import com.br.taximeapi.infra.database.postgresql.entity.UserEntity
import com.tyro.oss.arbitrater.arbitrater
import java.util.UUID

class UserEntityFactory {
    companion object {
        fun build(
            uuid: UUID? = UUID.randomUUID(),
            name: String? = null,
            email: String? = null,
            password: String? = null,
        ): UserEntity =
            UserEntity::class
                .arbitrater()
                .generateNulls(value = true)
                .withValue("uuid", uuid)
                .withValue("name", name)
                .withValue("password", password)
                .withValue("email", email)
                .createInstance()
    }
}
