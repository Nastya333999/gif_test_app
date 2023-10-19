plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.giphy.core"
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
    implementation(AppDependencies.Kotlin.serialization)
    implementation(AppDependencies.Hilt.hilt)
    implementation(AppDependencies.Hilt.navigationCompose)
    implementation(AppDependencies.Compose.preview)
    kapt(AppDependencies.Hilt.kapt)

    implementation(AppDependencies.Compose.activity)
    implementation(AppDependencies.Compose.runtime)
    implementation(AppDependencies.Compose.material3)
    implementation(AppDependencies.Compose.ui)
    implementation(AppDependencies.Compose.navigation)

    implementation(AppDependencies.Kotlin.core)
}