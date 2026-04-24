plugins {
    id("9craft-common")
    id("xyz.jpenilla.run-paper")
}

tasks.withType<Jar> {
    archiveBaseName = project.name.removeSuffix("-plugin")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    processResources {
        val props = mapOf("version" to version)
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }

    runServer {
        jvmArgs("-Xms4G", "-Xmx4G")
        minecraftVersion("26.1.2")
    }
}
