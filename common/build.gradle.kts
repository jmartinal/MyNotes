val coroutinesVersion: String by rootProject.project
val ktorVersion: String by rootProject.project

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
}

group = "com.jmartinal.mynotes"
version = "1.0-SNAPSHOT"

kotlin {
    jvm("desktop") {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(compose.materialIconsExtended)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }
        val desktopTest by getting
    }
}
