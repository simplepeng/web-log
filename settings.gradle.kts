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
    }
}

rootProject.name = "web-log"
include(":app")
include(":library")
include(":library-compose-ui")
include(":library-ui")
include(":library-no-op")
include(":library-ui-no-op")
include(":library-compose-ui-no-op")
include(":library-base")
