apply {
    from("$rootDir/android-library-build.gradle")
}
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    buildFeatures {
        compose = false

    }
    namespace = "${rootProject.extra.get("applicationId")}.core.database"
}


dependencies {

    "implementation"(project(":core:utils"))

    "implementation"(libs.bundles.room)
    ksp(libs.roomCompiler)
}
