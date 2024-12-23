apply {
    from("$rootDir/android-library-build.gradle")
}

plugins {
    alias(libs.plugins.android.library)
    id("kotlinx-serialization")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}
android {
    namespace = "${rootProject.extra.get("applicationId")}.home"

    defaultConfig {
        testInstrumentationRunner = "com.example.home.runner.CustomTestRunner"
    }
}

dependencies {

    val composeBom = platform(libs.androidx.compose.bom)

    "implementation"(composeBom)
    "implementation"(libs.bundles.jetpackCompost)
    "implementation"(libs.hiltNavigationCompose)
    "implementation"(libs.ktorAndroid)
    "implementation"(libs.bundles.archComponents)
    "implementation"(libs.bundles.kotlinCoroutines)

    "implementation"(libs.bundles.room)
    ksp(libs.roomCompiler)

    "implementation"(project(":core:sharedData"))
    "implementation"(project(":core:network"))
    "implementation"(project(":core:utils"))
    "implementation"(project(":core:ui"))
    "implementation"(project(":core:database"))


    "testImplementation"(libs.ktor.client.mock)
    "testImplementation"(libs.ktorContentNegotiation)
    "testImplementation"(libs.ktorSerialization)
    "kspTest"( libs.hilt.compiler)

    "testImplementation"(libs.bundles.unit.test)
    "androidTestImplementation"(libs.bundles.ui.test)
    "kspAndroidTest"(libs.hiltDaggerCompiler)
    "kspAndroidTest"( libs.hilt.compiler)


    "debugImplementation"(libs.androidx.ui.test.manifest)
}
