import io.papermc.paperweight.tasks.RebuildGitPatches
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    id("io.papermc.paperweight.patcher") version "2.0.0-beta.14"
}

val paperMavenPublicUrl = "https://repo.papermc.io/repository/maven-public/"

paperweight {
    upstreams.paper {
        ref = providers.gradleProperty("paperCommit")

        patchFile {
            path = "paper-server/build.gradle.kts"
            outputFile = file("soju-combat-server/build.gradle.kts")
            patchFile = file("soju-combat-server/build.gradle.kts.patch")
        }
        patchFile {
            path = "paper-api/build.gradle.kts"
            outputFile = file("soju-combat-api/build.gradle.kts")
            patchFile = file("soju-combat-api/build.gradle.kts.patch")
        }
        patchDir("paperApi") {
            upstreamPath = "paper-api"
            excludes = setOf("build.gradle.kts")
            patchesDir = file("soju-combat-api/paper-patches")
            outputDir = file("paper-api")
        }
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release = 21
        options.isFork = true
    }
    tasks.withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }
    tasks.withType<ProcessResources> {
        filteringCharset = Charsets.UTF_8.name()
    }
    tasks.withType<Test> {
        testLogging {
            showStackTraces = true
            exceptionFormat = TestExceptionFormat.FULL
            events(TestLogEvent.STANDARD_OUT)
        }
    }
    tasks.withType<AbstractArchiveTask>().configureEach {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
    }

    repositories {
        mavenCentral()
        maven(paperMavenPublicUrl)
    }
}

allprojects {
    tasks.withType<RebuildGitPatches>().configureEach {
        filterPatches = false
    }
}
