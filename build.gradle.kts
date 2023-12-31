plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.21"
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.2.1"
    id("io.micronaut.aot") version "4.2.1"
}

group = "br.dev.profbrunolopes"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Micronaut Framework
    ksp("io.micronaut:micronaut-http-validation:3.9.2")
    ksp("io.micronaut.serde:micronaut-serde-processor:1.5.2")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime:3.2.2")
    implementation("io.micronaut.serde:micronaut-serde-jackson:1.5.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.21")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.21")
    compileOnly("io.micronaut:micronaut-http-client:3.8.7")
    runtimeOnly("ch.qos.logback:logback-classic:1.4.12")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    testImplementation("io.micronaut:micronaut-http-client:3.8.7")

    // Micronaut Kotest
    ksp("io.micronaut:micronaut-inject-java:3.8.7")
    testImplementation("io.micronaut.test:micronaut-test-kotest:3.9.2")
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.5")

    // Micronaut Data JDBC, Flyway e PostgreSQL
    ksp("io.micronaut.data:micronaut-data-processor:4.4.1")
    implementation("io.micronaut.data:micronaut-data-jdbc:4.4.1:")
    implementation("io.micronaut.flyway:micronaut-flyway:6.2.1")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari:5.4.0")
    runtimeOnly("org.postgresql:postgresql:42.7.1")
    runtimeOnly("com.h2database:h2:2.2.224")

}

application {
    mainClass.set("br.dev.profbrunolopes.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("br.dev.profbrunolopes.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
}