plugins {
    id("yggdrasil-api")
}

scmVersion.tag.prefix = "map-api-"
version = scmVersion.version
description = "API for plugins integrating with Yggdrasil Map"

dependencies {
    compileOnly(libs.jetbrains.annotations)
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
