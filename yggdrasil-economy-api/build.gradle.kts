plugins {
    id("yggdrasil-api")
}

scmVersion.tag.prefix = "economy-api-"
version = scmVersion.version
description = "Yggdrasil economy API"

dependencies {
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
