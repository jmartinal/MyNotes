plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.compose)
}

group = "com.jmartinal.mynotes"
version = "1.0-SNAPSHOT"

kotlin {
    applyDefaultHierarchyTemplate()

    jvm("desktop") {
        jvmToolchain(17)
    }

    js(IR) {
        browser()
    }

    androidTarget() {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            implementation(compose.materialIconsExtended)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.negotiation)
            implementation(libs.ktor.serialization)
        }
        commonTest

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
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

        androidMain.dependencies {
            implementation(libs.kotlin.coroutines.android)
            implementation(libs.ktor.client.okhttp)
        }
        val androidUnitTest by getting
    }
}

android {
    namespace = "com.jmartinal.mynotes.common"
//    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
