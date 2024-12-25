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


dependencies {

    val composeBom = platform(libs.androidx.compose.bom)
    "implementation"(composeBom)
    "implementation"(libs.bundles.jetpackCompost)
    "implementation"(libs.hiltNavigationCompose)
    "implementation"(libs.bundles.archComponents)
    "implementation"(libs.bundles.kotlinCoroutines)
    "implementation"(libs.ktorAndroid)

    "implementation"(project(":core:utils"))
    "implementation"(project(":core:ui"))
    "implementation"(project(":core:sharedData"))
    "implementation"(project(":core:network"))


    "testImplementation"(libs.ktor.client.mock)
    "testImplementation"(libs.ktorContentNegotiation)
    "testImplementation"(libs.ktorSerialization)
    "kspTest"( libs.hilt.compiler)
    "testImplementation"(libs.bundles.unit.test)

    "androidTestImplementation"(libs.bundles.ui.test)
    "kspAndroidTest"(libs.hiltDaggerCompiler)
    "kspAndroidTest"( libs.hilt.compiler)


}
