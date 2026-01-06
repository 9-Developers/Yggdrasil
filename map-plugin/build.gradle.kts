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
