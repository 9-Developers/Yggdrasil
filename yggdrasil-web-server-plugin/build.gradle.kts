plugins {
    id("yggdrasil-plugin")
}

scmVersion.tag.prefix = "web-server-plugin-"
version = scmVersion.version
description = "Utility plugin for hosting a web server"

dependencies {
    compileOnly(libs.jetbrains.annotations)
    compileOnly(project(":yggdrasil-data-api"))
    compileOnly(project(":yggdrasil-web-server-api"))
    compileOnly(platform(libs.jackson.bom))
    compileOnly(libs.bundles.jackson)
    compileOnly(libs.bundles.jakarta)
    compileOnly(platform(libs.jersey.bom))
    compileOnly(libs.bundles.jersey)
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Jar> {
    from(project(":yggdrasil-web-server-api").sourceSets.main.get().output)
}
