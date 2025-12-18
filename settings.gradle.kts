plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "9craft Web Server"
include("web-server-api", "web-server-plugin")
