plugins {
    id("yggdrasil-api")
}

scmVersion.tag.prefix = "data-api-"
version = scmVersion.version
description = "API for plugins integrating with Yggdrasil Data"

dependencies {
    compileOnly(libs.jetbrains.annotations)
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
