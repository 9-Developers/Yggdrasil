import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    checkstyle
    `java-library`

    id("pl.allegro.tech.build.axion-release")
}

group = "tech.yggdrasil"

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

repositories {
    mavenCentral()

    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencyLocking {
    lockAllConfigurations()
}

checkstyle {
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
    toolVersion = "13.7.0"
}

configurations.configureEach {
    resolutionStrategy.force(
        "com.fasterxml.jackson.core:jackson-core:${libs.findVersion("jackson-legacy").get().requiredVersion}",
        "com.fasterxml.jackson.core:jackson-databind:${libs.findVersion("jackson-legacy").get().requiredVersion}",
        "org.apache.commons:commons-lang3:${libs.findVersion("commons-lang").get().requiredVersion}",
        "org.apache.logging.log4j:log4j-api:${libs.findVersion("log4j").get().requiredVersion}",
        "org.codehaus.plexus:plexus-utils:${libs.findVersion("plexus-utils").get().requiredVersion}",
    )
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
