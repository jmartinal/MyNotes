plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.compose)
}

group = "com.jmartinal.mynotes"
version = "1.0-SNAPSHOT"

kotlin {
    jvm("desktop") {
        jvmToolchain(17)
    }

    js(IR) {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.negotiation)
            implementation(libs.ktor.serialization)
        }
        commonTest

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(compose.materialIconsExtended)
                implementation(libs.kotlin.coroutines.core)

                implementation(libs.ktor.client.okhttp)
            }
        }
        val desktopTest by getting

        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(compose.runtime)

            implementation(libs.ktor.client.js)
        }
        jsTest
    }
}
