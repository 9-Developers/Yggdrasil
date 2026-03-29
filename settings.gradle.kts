plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "9craft"
include("api-map", "api-web-server", "plugin-map", "plugin-web-server")
