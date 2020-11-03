buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()

        maven("https://dl.bintray.com/icerockdev/plugins")
    }
    dependencies {
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
        classpath("com.android.tools.build:gradle:4.0.2")
        classpath("dev.icerock.moko:resources-generator:0.13.1")
//        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.10")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.0")
    }
}

group = "com.brocoding.bonder"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()
        maven("https://dl.bintray.com/icerockdev/moko")
    }
}