plugins {
    id("yggdrasil-api")
}

scmVersion.tag.prefix = "chat-api-"
version = scmVersion.version
description = "Yggdrasil chat API"

dependencies {
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
