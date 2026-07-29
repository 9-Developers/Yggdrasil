import org.jreleaser.model.Active

plugins {
    id("yggdrasil-common")

    `maven-publish`

    id("org.jreleaser")
}

java {
    withJavadocJar()
    withSourcesJar()
}

jreleaser {
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active = Active.ALWAYS
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository("build/staging-deploy")
                }
            }
        }
    }

    signing {
        active = Active.ALWAYS
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                groupId = project.group as String
                name = project.name
                description = project.description
                url = "https://gitlab.com/9developers/Yggdrasil"
                developers {
                    developer {
                        id = "Ixirsii"
                        name = "Ryan Porterfield"
                        email = "ixirsii@9dev.tech"
                    }
                }
                licenses {
                    license {
                        name = "BSD 3-Clause"
                        url = "https://opensource.org/license/bsd-3-clause/"
                    }
                }
                scm {
                    connection = "scm:git:git@gitlab.com:9developers/Yggdrasil.git"
                    developerConnection = "scm:git:git@gitlab.com:9developers/Yggdrasil.git"
                    url = "https://gitlab.com/9developers/Yggdrasil"
                }
            }
        }
    }

    repositories {
        maven {
            url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
        }
    }
}
