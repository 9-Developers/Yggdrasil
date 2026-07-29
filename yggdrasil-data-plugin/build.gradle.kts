plugins {
    id("yggdrasil-plugin")
}

scmVersion.tag.prefix = "data-plugin-"
version = scmVersion.version
description = "Utility plugin for persisting data to a database"

dependencies {
    compileOnly(libs.evo.inflector)
    compileOnly(libs.jetbrains.annotations)
    compileOnly(platform(libs.mongo.bom))
    compileOnly(libs.mongo.driver)
    compileOnly(libs.paper.api)
    compileOnly(platform(libs.reactor.bom))
    compileOnly(libs.reactor.core)

    implementation(project(":yggdrasil-data-api"))
    implementation(libs.jspecify)
    implementation(libs.slf4j.api)
    implementation(libs.sqlite)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly(libs.bundles.junit)
}

tasks.withType<Jar> {
    from(project(":yggdrasil-data-api").sourceSets.main.get().output)
}
