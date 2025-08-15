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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "web-log"
include(":app")
//
include(":library-base")
//
include(":library")
include(":library-no-op")
//
//include(":library-ui")
//include(":library-ui-no-op")
//
//include(":library-compose-ui")
//include(":library-compose-ui-no-op")
