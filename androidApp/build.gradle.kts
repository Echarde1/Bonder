plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}
group = "com.brocoding.bonder"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.brocoding.bonder.androidApp"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.10"
        kotlinCompilerExtensionVersion = "1.0.0-alpha03"
    }
}
dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.3")

    // Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.8")

    // Compose
    implementation("androidx.compose.ui:ui:1.0.0-alpha06")
    // Tooling support (Previews, etc.)
    implementation("androidx.ui:ui-tooling:1.0.0-alpha06")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.0-alpha06")
    // Material Design
    implementation("androidx.compose.material:material:1.0.0-alpha06")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.0.0-alpha06")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-alpha06")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-alpha06")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.0.0-alpha06")

    // UI Tests
    androidTestImplementation("androidx.ui:ui-test:1.0.0-alpha06")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.1")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
}
