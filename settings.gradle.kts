plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "yggdrasil"

include("yggdrasil-chat-api")
include("yggdrasil-core-api")
include("yggdrasil-data-api", "yggdrasil-data-plugin")
include("yggdrasil-economy-api")
include("yggdrasil-map-api", "yggdrasil-map-plugin")
include("yggdrasil-permission-api")
include("yggdrasil-web-server-api", "yggdrasil-web-server-plugin")
