plugins {
    alias(libs.plugins.axion)
}

scmVersion {
    tag {
        prefix = "web-server-plugin-"
    }
}

version = scmVersion.version
description = "PaperMC plugin for hosting a web server"

dependencies {
    compileOnly(libs.paper.api)
    compileOnly(project(":web-server-api"))

    implementation(libs.grizzly.http.server)
    implementation(platform(libs.jackson.bom))
    implementation(libs.bundles.jackson)
    implementation(libs.bundles.jakarta)
    implementation(libs.bundles.jersey)
    implementation(libs.jspecify)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly(libs.junit.platform.launcher)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.withType<Jar> {
    archiveBaseName = "9craft-web-server"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(project(":web-server-api").sourceSets.main.get().output)
}
