pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io")  }
    }
}

rootProject.name = "Giphy"
include(":app")
include(":gif:data")
include(":gif:di")
include(":gif:domain")
include(":core")
