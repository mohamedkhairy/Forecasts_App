plugins {
    alias(libs.plugins.kotlin.compose)
}
apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {

    val composeBom = platform(libs.androidx.compose.bom)
    "implementation"(composeBom)
    "implementation"(libs.bundles.jetpackCompost)
    "implementation"(libs.coil.compose)

}
