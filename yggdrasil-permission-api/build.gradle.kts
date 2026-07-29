plugins {
    id("yggdrasil-api")
}

scmVersion.tag.prefix = "permission-api-"
version = scmVersion.version
description = "Yggdrasil permission API"

dependencies {
    compileOnly(libs.jetbrains.annotations)
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
