import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    kotlin("jvm")
    id("jacoco-report-aggregation")
    id("org.jlleitschuh.gradle.ktlint")
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

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.testCodeCoverageReport {
    reports {
        xml.required.set(true)
        xml.outputLocation.set(file(layout.buildDirectory.dir("jacoco/jacoco.xml")))

        html.required.set(true)
        html.outputLocation.set(file(layout.buildDirectory.dir("jacoco/jacocoHtml")))

        csv.required.set(true)
        csv.outputLocation.set(file(layout.buildDirectory.dir("jacoco/jacoco.csv")))
    }
}

tasks.named("jacocoTestReport") {
    dependsOn(tasks.named<JacocoReport>("testCodeCoverageReport"))
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    additionalEditorconfig.set(
        mapOf(
            "indent_size" to "4",
            "insert_final_newline" to "true",
            "max_line_length" to "off",
        ),
    )
}
