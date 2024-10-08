import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)


    kotlin("plugin.serialization") version "1.5.0"

    id("kotlin-parcelize")
    id("kotlin-kapt")

    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.project.imagesearchingadvancedapplication"
    compileSdk = 34

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.project.imagesearchingadvancedapplication"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String",
            "KAKAO_API_KEY",
            properties.getProperty("kakao_app_key")
        )
        buildConfigField("String",
            "KAKAO_REST_KEY",
            properties.getProperty("kakao_rest_key")
        )

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        viewBinding {
            enable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    testImplementation(libs.kotlinx.coroutines.test)
    implementation(libs.glide)
    kapt("com.github.bumptech.glide:compiler:4.14.2")


    implementation(libs.androidx.navigation.ui.ktx.v240)

    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
}