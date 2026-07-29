plugins {
    id("yggdrasil-api")
}

scmVersion.tag.prefix = "web-server-api-"
version = scmVersion.version
description = "API for plugins integrating with Yggdrasil Web Server"

dependencies {
    compileOnly(libs.jakarta)
    compileOnly(libs.jakarta.validations.api)
    compileOnly(platform(libs.jersey.bom))
    compileOnly(libs.jersey.container.grizzly)
    compileOnly(libs.jersey.hk2)
    compileOnly(libs.paper.api)
    compileOnly(libs.slf4j.api)

    implementation(libs.jspecify)
}
