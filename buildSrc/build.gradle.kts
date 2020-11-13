plugins {
    id("org.jetbrains.kotlin.jvm") version("1.4.0")
}

repositories {
    mavenLocal()

    jcenter()
    google()

    gradlePluginPortal()

    maven { url = uri("https://dl.bintray.com/icerockdev/plugins") }
}

dependencies {
    implementation("com.android.tools.build:gradle:4.0.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.4.0")
    implementation("dev.icerock:mobile-multiplatform:0.7.0")
}