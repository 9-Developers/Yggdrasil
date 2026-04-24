plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "9craft"

include("9craft-chat-api")
include("9craft-core-api")
include("9craft-data-api", "9craft-data-plugin")
include("9craft-economy-api")
include("9craft-map-api", "9craft-map-plugin")
include("9craft-permission-api")
include("9craft-web-server-api", "9craft-web-server-plugin")
