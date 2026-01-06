plugins {
    alias(libs.plugins.axion)
}

scmVersion {
    tag {
        prefix = "map-plugin-"
    }
}

version = scmVersion.version
description = "PaperMC plugin which provides a web map"

dependencies {
    compileOnly(libs.paper.api)

    implementation(libs.bundles.jakarta)
    implementation(libs.jersey.hk2)
    implementation(libs.jspecify)
    implementation(project(":map-api"))
    implementation(project(":web-server-api"))

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly(libs.junit.platform.launcher)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
