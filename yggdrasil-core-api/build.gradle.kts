plugins {
    id("yggdrasil-api")
}

scmVersion.tag.prefix = "core-api-"
version = scmVersion.version
description = "Core API for Yggdrasil plugins"

dependencies {
    compileOnly(libs.jetbrains.annotations)
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
