plugins {
    kotlin("multiplatform")
    id("com.android.library")

    // adding kotlin serialization plugin to handle serialization over network
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()


    // this is the android target configuration for the business library
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        // creating new source set for androidDebug and androidRelease
        sourceSets {
            val androidMain by getting

            // creating a new source set for androidDebug
            val androidDebug by creating {
                dependsOn(androidMain)
                kotlin.srcDir("src/androidDebug/kotlin")
            }

            // creating a new source set for androidRelease
            val androidRelease by creating {
                dependsOn(androidMain)
                kotlin.srcDir("src/androidRelease/kotlin")
            }
        }
    }

    // the following targets configuration are for iOS on the business library
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    ios {
        compilations["main"].cinterops {
            create("interop") {
                defFile = file("src/iosMain/kotlin/com/techbyfloig/cubannotary/business/base/interop.def")
                headers = files("src/iosMain/kotlin/com/techbyfloig/cubannotary/business/base/interop.h")
            }
        }
    }

    // the following are the source sets automatically configured by each of the above targets
    sourceSets {
        // specifies the ktor version to use
        val ktorVersion = "2.3.4"
        val koin = "3.2.0"

        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here

                implementation("io.ktor:ktor-client-core:$ktorVersion") // add ktor client for common source set
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion") // responsible for serializing/deserializing the content in a specific format
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion") // instruct ktor to use the json format when serializing/deserializing
                implementation("io.ktor:ktor-client-resources:$ktorVersion") // ktor plugin for handling resources with type safe

                implementation("io.insert-koin:koin-core:$koin") // adding dependency injection

                // enable coroutine in common source set
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
            }
        }

        val iosMain by getting {
            dependsOn(commonMain)

            dependencies {
                // ktor client for iOS
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}


// this is to produce an android library that can be used by other android apps
// configure this at will
android {
    namespace = "com.techbyfloig.cubannotary.business"
    compileSdk = 33
    defaultConfig {
        minSdk = 23
    }
}

