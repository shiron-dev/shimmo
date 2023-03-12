import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.20-RC"
    application
}

group = "dev.shiron"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "spigotmc-repo"
        setUrl("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "sonatype"
        setUrl("https://oss.sonatype.org/content/groups/public/")
    }
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<Jar> {
    val include = setOf("kotlin-stdlib-1.8.20-RC.jar")

    configurations.runtimeClasspath.get()
        .filter { it.name in include }
        .map { zipTree(it) }
        .also { from(it) }
}
