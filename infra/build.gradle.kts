plugins {
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "1.9.23"

    id("kotlin-conventions")
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.br.taximeapi.infra"

version = "0.0.1"
description =
    "Api responsável por gerenciar corridas entre usuários e os motoristas"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("com.tyro.oss:arbitrater:1.0.1")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

val applicationCoverageExclusions =
    listOf(
        "**/*ApplicationKt**",
        "**/*dto/**",
        "**/*configuration/**",
        "**/*config/**",
        "**/*entity/**",
        "**/*exceptions/**",
    )

tasks.withType<JacocoReport> {
    afterEvaluate {
        classDirectories.setFrom(
            files(
                classDirectories.files.map {
                    fileTree(it).apply {
                        exclude(applicationCoverageExclusions)
                    }
                },
            ),
        )
    }
}
