plugins {
    alias(libs.plugins.axion)
}

scmVersion {
    tag {
        prefix = "plugin-web-server-"
    }
}

version = scmVersion.version
description = "PaperMC plugin for hosting a web server"

dependencies {
    compileOnly(libs.paper.api)

    implementation(platform(libs.jackson.bom))
    implementation(libs.bundles.jackson)
    implementation(libs.bundles.jakarta)
    implementation(platform(libs.jersey.bom))
    implementation(libs.bundles.jersey)
    implementation(libs.jspecify)
    implementation(project(":api-web-server"))

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Jar> {
    archiveBaseName = "9craft-web-server"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(project(":api-web-server").sourceSets.main.get().output)
}
