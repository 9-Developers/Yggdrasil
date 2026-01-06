import org.jreleaser.model.Active

plugins {
    `maven-publish`

    alias(libs.plugins.axion)
    alias(libs.plugins.jreleaser)
}

scmVersion {
    tag {
        prefix = "web-server-api-"
    }
}

version = scmVersion.version
description = "API for plugins integrating with 9craft Web Server"

dependencies {
    api(libs.grizzly.http.server)
    api(libs.jspecify)

    compileOnly(libs.paper.api)

    implementation(libs.jakarta)
    implementation(libs.jakarta.validations.api)
    implementation(libs.jersey.hk2)
    implementation(libs.slf4j.api)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }

    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Jar> {
    archiveBaseName = "9craft-web-server-api"
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
                groupId = "tech.9craft"
                name = "9craft-web-server-api"
                description = project.description
                url = "https://gitlab.com/9developers/9craft"
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
                    connection = "scm:git:git@gitlab.com:9developers/9craft.git"
                    developerConnection = "scm:git:git@gitlab.com:9developers/9craft.git"
                    url = "https://gitlab.com/9developers/9craft"
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
