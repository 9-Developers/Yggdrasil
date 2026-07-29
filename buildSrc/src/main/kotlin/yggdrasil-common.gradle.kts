plugins {
    checkstyle
    `java-library`

    id("pl.allegro.tech.build.axion-release")
}

group = "tech.yggdrasil"

repositories {
    mavenCentral()

    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

checkstyle {
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
    toolVersion = "13.7.0"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
