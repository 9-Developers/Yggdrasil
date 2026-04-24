plugins {
    id("9craft-api")
}

scmVersion.tag.prefix = "map-api-"
version = scmVersion.version
description = "API for plugins integrating with 9craft Map"

dependencies {
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
