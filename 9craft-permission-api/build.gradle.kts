plugins {
    id("9craft-api")
}

scmVersion.tag.prefix = "permission-api-"
version = scmVersion.version
description = "9craft permission API"

dependencies {
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
