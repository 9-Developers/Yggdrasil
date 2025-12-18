plugins {
    checkstyle
    `java-library`

    alias(libs.plugins.axion)
}

scmVersion {
    tag {
        prefix = ""
    }
}

version = scmVersion.version
description = "API for plugins integrating with 9craft Web Server"

dependencies {
    implementation(libs.grizzly.http.server)
    implementation(libs.jakarta)
    implementation(libs.jspecify)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
