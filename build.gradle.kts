plugins {
    java
}

group = "tech.9dev"

subprojects {
    repositories {
        mavenCentral()

        maven {
            name = "papermc"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
