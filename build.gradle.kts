import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    kotlin("jvm").version(Kotlin.version)
    kotlin("plugin.allopen").version(Kotlin.version)
    kotlin("plugin.serialization").version(Kotlin.version)

    id(Shadow.pluginId) version (Shadow.version)
    // Apply the application plugin to add support for building a CLI application.
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "13"
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    mavenCentral()
    maven("https://packages.confluent.io/maven")
    maven("https://jitpack.io")
    mavenLocal()
}

dependencies {
    implementation(DittNAV.Common.utils)
    implementation(Hikari.cp)
    implementation(Kotlinx.coroutines)
    implementation(Ktor.authJwt)
    implementation(Ktor.htmlBuilder)
    implementation(Ktor.serialization)
    implementation(Ktor.serverNetty)
    implementation(Logback.classic)
    implementation(Logstash.logbackEncoder)
    implementation(Prometheus.common)
    implementation(Prometheus.hotspot)
    implementation(Prometheus.logback)
    implementation(Tms.KtorTokenSupport.azureValidation)

    runtimeOnly(Oracle.ojdbc10)

    testImplementation(H2Database.h2)
    testImplementation(Junit.api)
    testImplementation(Kluent.kluent)
    testImplementation(Mockk.mockk)

    testRuntimeOnly(Junit.engine)
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
            events("passed", "skipped", "failed")
        }
    }
}

apply(plugin = Shadow.pluginId)
