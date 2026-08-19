plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencyLocking {
    lockAllConfigurations()
}

configurations.configureEach {
    resolutionStrategy.force(
        "com.fasterxml.jackson.core:jackson-core:${libs.versions.jackson.legacy.get()}",
        "com.fasterxml.jackson.core:jackson-databind:${libs.versions.jackson.legacy.get()}",
        "org.apache.httpcomponents.client5:httpclient5:${libs.versions.httpclient5.get()}",
        "org.apache.httpcomponents.core5:httpcore5-h2:${libs.versions.httpcore5.get()}",
        "org.apache.tika:tika-core:${libs.versions.tika.get()}",
        "org.codehaus.plexus:plexus-utils:${libs.versions.plexus.utils.get()}",
    )
}

dependencies {
    implementation(libs.axion)
    implementation(libs.jreleaser)
    implementation(libs.run.paper)
}
