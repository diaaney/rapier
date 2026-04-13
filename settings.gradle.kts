pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "soju-combat"

include("soju-combat-api", "soju-combat-server")
for (name in listOf("soju-combat-api", "soju-combat-server")) {
    findProject(":$name")!!.projectDir = file(name)
}
