plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "9craft"
include("map-api", "map-plugin", "web-server-api", "web-server-plugin")
