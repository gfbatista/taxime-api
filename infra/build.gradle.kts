plugins {
    kotlin("plugin.spring") version "1.9.25"

    id("kotlin-conventions")
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.br.taximeapi"

repositories {
    mavenCentral()
}

tasks.bootJar {
    enabled = true

    mainClass.set("com.luizalabs.taximeapi.TaximeApiApplication")
    archiveBaseName.set("application")
    destinationDirectory.set(file("${layout.buildDirectory.get()}/libs"))
}

tasks.jar {
    enabled = false
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
