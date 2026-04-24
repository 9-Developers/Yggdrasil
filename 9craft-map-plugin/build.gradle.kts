plugins {
    id("9craft-plugin")
}

scmVersion.tag.prefix = "map-plugin-"
version = scmVersion.version
description = "Paper plugin which provides a web map"

dependencies {
    compileOnly(project(":9craft-map-api"))
    compileOnly(project(":9craft-web-server-api"))
    compileOnly(libs.bundles.jakarta)
    compileOnly(platform(libs.jersey.bom))
    compileOnly(libs.jersey.container.grizzly)
    compileOnly(libs.jersey.hk2)
    compileOnly(libs.paper.api)

    implementation(libs.jspecify)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly(libs.bundles.junit)
}

tasks.withType<Jar> {
    from(project(":9craft-map-api").sourceSets.main.get().output)
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.addAll(listOf("--add-modules", "jdk.incubator.vector"))
}

tasks.withType<Test>().configureEach {
    jvmArgs("--add-modules", "jdk.incubator.vector")
}
