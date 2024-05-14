plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.compose)
    alias(libs.plugins.androidLibrary)
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
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                implementation(compose.materialIconsExtended)

                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.negotiation)
                implementation(libs.ktor.serialization)

                implementation(libs.voyager.navigator)
            }
        }
        val commonTest by getting

        val composeKmpCommonMain by creating {
            dependsOn(commonMain)
        }

        val desktopMain by getting {
            dependsOn(composeKmpCommonMain)
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlin.coroutines.core)

                implementation(libs.ktor.client.okhttp)
            }
        }
        val desktopTest by getting

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)

                implementation(libs.ktor.client.js)
            }
        }
        val jsTest by getting

        val androidMain by getting {
            dependsOn(composeKmpCommonMain)
            dependencies {
                implementation(libs.kotlin.coroutines.android)
                implementation(libs.ktor.client.okhttp)
            }
        }
        val androidUnitTest by getting
    }
}

android {
    namespace = "com.jmartinal.mynotes.common"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
