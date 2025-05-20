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

rootProject.name = "AspireTask"
include(":app")
include(":features:searchInGithubRepositoriesFeature")
include(":domain:searchUseCases")
include(":data:services:networkServices:githubRepositoriesService")
include(":data:repositories")
include(":core")
include(":core:core-ui")
include(":core:core-network")
include(":core:core-navigations")
