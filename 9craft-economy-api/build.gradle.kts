plugins {
    id("9craft-api")
}

scmVersion.tag.prefix = "economy-api-"
version = scmVersion.version
description = "9craft economy API"

dependencies {
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
