plugins {
    id("kotlin-conventions")
}

group = "com.br.taximeapi.application"

dependencies {
    implementation(project(":domain"))
}
