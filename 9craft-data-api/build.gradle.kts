plugins {
    id("9craft-api")
}

scmVersion.tag.prefix = "data-api-"
version = scmVersion.version
description = "API for plugins integrating with 9craft Data"

dependencies {
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
