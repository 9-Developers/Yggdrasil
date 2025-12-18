plugins {
    checkstyle
    `java-library`

    alias(libs.plugins.axion)
    alias(libs.plugins.library.conventions)
}

scmVersion {
    tag {
        prefix = ""
    }
}

group = "tech.9dev"
version = "1.0.0-SNAPSHOT"
description = "API for plugins integrating with 9craft Web Server"

dependencies {
    implementation(libs.grizzly.http.server)
    implementation(libs.jakarta)
    implementation(libs.jspecify)
}
