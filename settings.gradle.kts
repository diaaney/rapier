pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "rapier"

include("rapier-api", "rapier-server")
for (name in listOf("rapier-api", "rapier-server")) {
    findProject(":$name")!!.projectDir = file(name)
}
