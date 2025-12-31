plugins {
    checkstyle
    java
}

group = "tech.9dev"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "checkstyle")

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
