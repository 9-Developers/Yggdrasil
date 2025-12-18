plugins {
    checkstyle
    java
}

group = "tech.9dev"

repositories {
    mavenCentral()
}

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

checkstyle {
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
    toolVersion = "12.3.0"
}
