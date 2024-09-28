plugins {
    `kotlin-dsl`
}

version = "0.0.1"
description = "Api responsável por gerenciar corridas entre usuários e os motoristas"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:2.0.0")
}
