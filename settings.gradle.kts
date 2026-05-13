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
    }
}

ProcessBuilder("git", "submodule", "update", "--init", "--recursive")
    .directory(rootDir)
    .start()
    .waitFor()

rootProject.name = "HR Connect"
include(":app")

include(":uikit")
project(":uikit").projectDir = File("hr-connect-uikit/uikit")