plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = AppConfig.namespace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.example.giphy"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(AppDependencies.Kotlin.core)

    implementation(AppDependencies.Hilt.hilt)
    implementation(AppDependencies.Hilt.navigationCompose)
    kapt(AppDependencies.Hilt.kapt)

    implementation(AppDependencies.Compose.activity)
    implementation(AppDependencies.Compose.runtime)
    implementation(AppDependencies.Compose.material3)
    implementation(AppDependencies.Compose.ui)
    implementation(AppDependencies.Compose.navigation)

    implementation(AppDependencies.Paging.compose)
    implementation(AppDependencies.Paging.core)
    implementation(AppDependencies.Kotlin.serialization)

    implementation(AppDependencies.Room.roomPaging)

    implementation(AppDependencies.Coil.coilKt)
    implementation(AppDependencies.Coil.coilCompose)
    implementation(AppDependencies.Coil.coilGif)

    // Local modules
    implementation(project(mapOf("path" to ":gif:di")))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":gif:domain")))
}
