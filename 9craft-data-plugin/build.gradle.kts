plugins {
    id("9craft-plugin")
}

scmVersion.tag.prefix = "data-plugin-"
version = scmVersion.version
description = "Utility plugin for persisting data to a database"

dependencies {
    compileOnly(platform(libs.mongo.bom))
    compileOnly(libs.mongo.driver)
    compileOnly(libs.paper.api)
    compileOnly(platform(libs.reactor.bom))
    compileOnly(libs.reactor.core)
    compileOnly(libs.slf4j.api)
    compileOnly(libs.sqlite)

    implementation(project(":9craft-data-api"))
    implementation(libs.jspecify)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly(libs.bundles.junit)
}

tasks.withType<Jar> {
    from(project(":9craft-data-api").sourceSets.main.get().output)
}
