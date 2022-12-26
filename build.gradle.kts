import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.flywaydb.flyway") version "9.8.3"
    kotlin("jvm") version "1.7.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

flyway {

}

dependencies {
    val ktor_version = "2.2.1"
    val slf4j_version = "2.0.5"
    val hikariCP_version = "5.0.1"
    val flywaydb_version = "9.8.3"
    val jetbrainsExposed_version = "0.40.1"
    val postgresql_version = "42.5.1"

    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("org.slf4j:slf4j-simple:$slf4j_version")

    implementation("com.zaxxer:HikariCP:$hikariCP_version")

    implementation("org.flywaydb:flyway-core:$flywaydb_version")

    implementation("org.jetbrains.exposed:exposed-core:$jetbrainsExposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$jetbrainsExposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$jetbrainsExposed_version")

    implementation("org.postgresql:postgresql:$postgresql_version")

    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-tests:$ktor_version")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("ApplicationKt")
}