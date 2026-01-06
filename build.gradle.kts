plugins {
    checkstyle
    java
}

group = "tech.9craft"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "checkstyle")
    apply(plugin = "java-library")

    repositories {
        mavenCentral()

        maven {
            name = "papermc"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
    }

    checkstyle {
        configFile = rootProject.file("config/checkstyle/checkstyle.xml")
        toolVersion = "12.3.0"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
