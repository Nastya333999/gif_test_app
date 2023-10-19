plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.giphy.gif.data"
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
    implementation(AppDependencies.Paging.compose)
    implementation(AppDependencies.Kotlin.core)
    implementation(AppDependencies.Kotlin.serialization)
    implementation(AppDependencies.Hilt.hilt)
    kapt(AppDependencies.Hilt.kapt)

    implementation(AppDependencies.Retrofit.retrofit)
    implementation(AppDependencies.Retrofit.retrofitConverter)


    implementation(AppDependencies.Okhttp3.okhttp)
    implementation(AppDependencies.Okhttp3.logging)

    implementation(AppDependencies.Room.roomPaging)
    implementation(AppDependencies.Room.roomRuntime)
    implementation(AppDependencies.Room.roomKtx)
    kapt(AppDependencies.Room.kart)

    // Local modules
    implementation(project(mapOf("path" to ":gif:domain")))
}
