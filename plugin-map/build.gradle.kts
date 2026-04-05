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
    implementation(platform(libs.jersey.bom))
    implementation(libs.jersey.container.grizzly)
    implementation(libs.jersey.hk2)
    implementation(libs.jspecify)
    implementation(project(":api-map"))
    implementation(project(":api-web-server"))

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly(libs.bundles.junit)
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.addAll(listOf("--add-modules", "jdk.incubator.vector"))
}

tasks.withType<Test>().configureEach {
    jvmArgs("--add-modules", "jdk.incubator.vector")
}
