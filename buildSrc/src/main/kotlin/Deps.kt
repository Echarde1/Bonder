import Deps.Libs.Versions.androidAppCompatVersion
import Deps.Libs.Versions.androidCoreTestingVersion
import Deps.Libs.Versions.androidLifecycleVersion
import Deps.Libs.Versions.coroutinesVersion
import Deps.Libs.Versions.ktorVersion
import Deps.Libs.Versions.materialDesignVersion
import Deps.Libs.Versions.mokoGraphicsVersion
import Deps.Libs.Versions.mokoMvvmVersion
import Deps.Libs.Versions.mokoParcelizeVersion

object Deps {
    private const val kotlinVersion = "1.4.0"

    private const val detektVersion = "1.7.4"

    const val mokoResourcesVersion = "0.13.0"

    object Android {
        const val compileSdk = 29
        const val targetSdk = 29
        const val minSdk = 16
    }

    object Plugins {
        val androidApplication = GradlePlugin(id = "com.android.application")
        val androidLibrary = GradlePlugin(id = "com.android.library")
        val kotlinJvm = GradlePlugin(id = "org.jetbrains.kotlin.jvm")
        val kotlinMultiplatform = GradlePlugin(id = "org.jetbrains.kotlin.multiplatform")
        val kotlinKapt = GradlePlugin(id = "kotlin-kapt")
        val kotlinAndroid = GradlePlugin(id = "kotlin-android")
        val kotlinAndroidExtensions = GradlePlugin(id = "kotlin-android-extensions")
//        val kotlinSerialization = GradlePlugin(id = "kotlin-serialization")
        val kotlinCocoaPods = GradlePlugin(id = "org.jetbrains.kotlin.native.cocoapods")
        val kotlinSerialization = GradlePlugin(id = "org.jetbrains.kotlin.plugin.serialization")
        val kotlinAndroidParcelize = GradlePlugin(id = "org.jetbrains.kotlin.android.extensions")
        val mavenPublish = GradlePlugin(id = "org.gradle.maven-publish")

        val mobileMultiplatform = GradlePlugin(id = "dev.icerock.mobile.multiplatform")
        val iosFramework = GradlePlugin(id = "dev.icerock.mobile.multiplatform.ios-framework")

        val mokoResources = GradlePlugin(
            id = "dev.icerock.mobile.multiplatform-resources",
            module = "dev.icerock.moko:resources-generator:$mokoResourcesVersion"
        )

        val detekt = GradlePlugin(
            id = "io.gitlab.arturbosch.detekt",
            version = detektVersion
        )
    }

    object Libs {
        private object Versions {
            const val androidAppCompatVersion = "1.2.0"
            const val materialDesignVersion = "1.2.1"
            const val androidLifecycleVersion = "2.2.0"
            const val androidCoreTestingVersion = "2.1.0"

            const val coroutinesVersion = "1.4.0"
            const val kotlinSerializationVersion = "1.0.1"

            const val ktorVersion = "1.4.1"

            const val mokoMvvmVersion = "0.8.0"
            const val mokoParcelizeVersion = "0.4.0"
            const val mokoGraphicsVersion = "0.4.0"
        }

        object Android {
            const val appCompat = "androidx.appcompat:appcompat:$androidAppCompatVersion"
            const val material = "com.google.android.material:material:$materialDesignVersion"
            const val lifecycle = "androidx.lifecycle:lifecycle-extensions:$androidLifecycleVersion"
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
            const val ktorAndroid = "io.ktor:ktor-client-android:$ktorVersion"
        }

        object iOS {
            const val ktorIos = "io.ktor:ktor-client-ios:$ktorVersion"
        }

        object MultiPlatform {
            const val kotlinCoroutines =
//                "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9-native-mt"
//            const val kotlinCoroutinesCommon = "org.jetbrains.kotlin:kotlin-stdlib:1.4.10"
            const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"
//            const val kotlinStdLibCommon = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0"
            const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"

            val mokoResources = MultiPlatformLibrary(
                common = "dev.icerock.moko:resources:$mokoResourcesVersion",
                iosArm64 = "dev.icerock.moko:resources-iosarm64:$mokoResourcesVersion",
                iosX64 = "dev.icerock.moko:resources-iosx64:$mokoResourcesVersion"
            )
            const val mokoMvvm = "dev.icerock.moko:mvvm:$mokoMvvmVersion"
            const val mokoParcelize = "dev.icerock.moko:parcelize:$mokoParcelizeVersion"
            const val mokoGraphics = "dev.icerock.moko:graphics:$mokoGraphicsVersion"

            const val ktorCore = "io.ktor:ktor-client-core:$ktorVersion"
        }

        object Tests {
            const val kotlinTestJUnit =
                "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
            const val androidCoreTesting =
                "androidx.arch.core:core-testing:$androidCoreTestingVersion"
        }
    }
}
