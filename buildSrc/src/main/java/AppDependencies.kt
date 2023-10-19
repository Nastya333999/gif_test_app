import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {

    //Hilt
    object Hilt {
        val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        val kapt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        val hiltKotlin = "com.google.dagger:hilt-core:${Versions.hilt}"
        val kaptKotlin = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        val navigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"
    }

    object Compose {
        val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        val ui = "androidx.compose.ui:ui:${Versions.composeVersion}"
        val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
        val material3 = "androidx.compose.material3:material3:${Versions.composeMaterial3Version}"
        val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigationVersion}"
        val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
        val runtime = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.composeVersion}"
    }

    object Kotlin {
        val core = "androidx.core:core-ktx:${Versions.kotlinCore}"
        val bom = "org.jetbrains.kotlin:kotlin-bom:${Versions.kotlinCore}"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
        val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCoreVersion}"
        val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
        val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerializationVersion}"
    }

    object Paging {
        val core    = "androidx.paging:paging-runtime:${Versions.pagingVersion}"
        val compose = "androidx.paging:paging-compose:${Versions.pagingComposeVersion}"
    }

    object Room {
        val roomKtx = "androidx.room:room-ktx:${Versions.roomKtxVersion}"
        val roomRuntime = "androidx.room:room-runtime:${Versions.roomKtxVersion}"
        val kart = "androidx.room:room-compiler:${Versions.roomKtxVersion}"
        val roomPaging = "androidx.room:room-paging:${Versions.pagingRoomVersion}"
    }

    object Okhttp3 {
        val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp3Version}"
        val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3Version}"
    }

    object Retrofit {
        val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        val retrofitConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitConverterVersion}"
    }

    object Coil {
        val coilKt = "io.coil-kt:coil:${Versions.coilVersion}"
        val coilGif = "io.coil-kt:coil-gif:${Versions.coilGifVersion}"
        val coilCompose = "io.coil-kt:coil-compose:${Versions.coilVersion}"
    }
}

//Util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}