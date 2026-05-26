pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

val submodules = listOf(
    "hr-connect-uikit",
    "hr-connect-netlib"
)

val emptySubmodules = submodules.filter { path ->
    val dir = File(rootDir, path)
    dir.listFiles().isNullOrEmpty()
}

if (emptySubmodules.isNotEmpty()) {
    ProcessBuilder("git", "submodule", "update", "--init", "--recursive")
        .directory(rootDir)
        .start()
        .waitFor()
}

rootProject.name = "HR Connect"
include(":app")

include(":uikit")
project(":uikit").projectDir = File("hr-connect-uikit/uikit")

include(":netlib")
project(":netlib").projectDir = File("hr-connect-netlib/netlib")