plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencyLocking {
    lockAllConfigurations()
}

dependencies {
    implementation(libs.axion)
    implementation(libs.jreleaser)
    implementation(libs.run.paper)
}
