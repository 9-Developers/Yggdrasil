plugins {
    `java-library`

    alias(libs.plugins.axion)
}

scmVersion {
    tag {
        prefix = "api-"
    }
}

version = scmVersion.version
description = "API for plugins integrating with 9craft Web Server"

dependencies {
    api(libs.jspecify)

    implementation(libs.grizzly.http.server)
    implementation(libs.jakarta)
    implementation(libs.slf4j.api)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }

    withJavadocJar()
    withSourcesJar()
}
