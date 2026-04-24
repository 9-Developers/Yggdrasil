plugins {
    id("9craft-api")
}

scmVersion.tag.prefix = "core-api-"
version = scmVersion.version
description = "Core API for 9craft plugins"

dependencies {
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
