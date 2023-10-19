plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.giphy.gif.di"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(AppDependencies.Kotlin.core)
    implementation (AppDependencies.Hilt.hilt)
    kapt (AppDependencies.Hilt.kapt)

    kapt(AppDependencies.Room.kart)
    implementation(AppDependencies.Room.roomRuntime)
    implementation(AppDependencies.Retrofit.retrofit)

    // Local modules
    implementation(project(mapOf("path" to ":gif:data")))
    implementation(project(mapOf("path" to ":gif:domain")))
}