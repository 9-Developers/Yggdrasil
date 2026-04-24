plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.axion)
    implementation(libs.jreleaser)
    implementation(libs.run.paper)
}
