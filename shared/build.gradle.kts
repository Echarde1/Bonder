import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    plugin(Deps.Plugins.androidLibrary)
    plugin(Deps.Plugins.kotlinMultiplatform)
    plugin(Deps.Plugins.mobileMultiplatform)
    plugin(Deps.Plugins.iosFramework)
    plugin(Deps.Plugins.kotlinSerialization)
    plugin(Deps.Plugins.kotlinAndroidParcelize)
}

kotlin {
    android()
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true) ::iosArm64
        else ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
}

dependencies {
    commonMainImplementation(Deps.Libs.MultiPlatform.kotlinCoroutines)
    commonMainImplementation(Deps.Libs.MultiPlatform.ktorCore)

    androidMainImplementation(Deps.Libs.Android.lifecycle)
    androidMainImplementation(Deps.Libs.Android.ktorAndroid)

    iosMainImplementation(Deps.Libs.iOS.ktorIos)

    commonMainApi(Deps.Libs.MultiPlatform.mokoParcelize)
    commonMainApi(Deps.Libs.MultiPlatform.kotlinSerialization)
}

val xcodeMode = System.getenv("CONFIGURATION") ?: "DEBUG"
val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(xcodeMode)
val frameworkFile = framework.outputFile

val xcodeFrameworksDirectory: File? = run {
    val xcodeTargetBuildDir = System.getenv("TARGET_BUILD_DIR") ?: return@run null
    val xcodeFrameworksFolderPath = System.getenv("FRAMEWORKS_FOLDER_PATH") ?: return@run null
    file("$xcodeTargetBuildDir/$xcodeFrameworksFolderPath")
}

val frameworksDirectory = File(buildDir, "xcode-frameworks")

val packForXcode by tasks.creating(Sync::class) {
    group = "build"

    //selecting the right configuration for the iOS framework depending on the Xcode environment variables
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)

    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)

    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\nexport 'JAVA_HOME=${System.getProperty("java.home")}'\ncd '${rootProject.rootDir}'\n./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)