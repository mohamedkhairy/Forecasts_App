plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("kotlin-parcelize")
}

apply("../config.gradle.kts")


android {
    namespace = "${rootProject.extra.get("applicationId")}"
    compileSdk = rootProject.extra.get("compileSdk").toString().toInt()

    defaultConfig {
        applicationId = "${rootProject.extra.get("applicationId")}"
        minSdk = rootProject.extra.get("minSdk").toString().toInt()
        targetSdk = rootProject.extra.get("compileSdk").toString().toInt()
        versionCode = rootProject.extra.get("versionCode").toString().toInt()
        versionName = "${rootProject.extra.get("versionName")}"

        testInstrumentationRunner = "${rootProject.extra.get("testRunner")}"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.coil.compose)

    implementation(libs.hilt)
    implementation(libs.hiltNavigationCompose)
    ksp(libs.hiltDaggerCompiler)

    implementation(project(":core:utils"))
    implementation(project(":core:ui"))
    implementation(project(":features:home"))
//    implementation(project(":features:Details"))
    

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
