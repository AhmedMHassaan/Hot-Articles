pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://jitpack.io")

        /*maven{
            url = uri("https://jitpack.io")
        }*/
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")


    }
}

rootProject.name = "OrangeTask"
include(":app")
include(":data")
include(":domain")
include(":core")
