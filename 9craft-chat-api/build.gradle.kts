plugins {
    id("9craft-api")
}

scmVersion.tag.prefix = "chat-api-"
version = scmVersion.version
description = "9craft chat API"

dependencies {
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)
}
