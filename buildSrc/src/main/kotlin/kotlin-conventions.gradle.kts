import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    kotlin("jvm")
    id("jacoco")
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

tasks.named("compileKotlin", KotlinCompilationTask::class.java) {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val coverageExclusions =
    listOf(
        "**/*ApplicationKt**",
    )

tasks.withType<JacocoReport> {
    afterEvaluate {
        classDirectories.setFrom(
            files(
                classDirectories.files.map {
                    fileTree(it).apply {
                        exclude(coverageExclusions)
                    }
                },
            ),
        )
    }
}

tasks.withType<JacocoReport> {
    dependsOn(tasks.withType(Test::class.java))

    reports {
        csv.required = true
        html.required = true
        xml.required = true

        csv.outputLocation.set(file(layout.buildDirectory.dir("jacoco/jacoco.csv")))
        html.outputLocation.set(file(layout.buildDirectory.dir("jacoco/jacocoHtml")))
        xml.outputLocation.set(file(layout.buildDirectory.dir("jacoco/jacoco.xml")))
    }
}
