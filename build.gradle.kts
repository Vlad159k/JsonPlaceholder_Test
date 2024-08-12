plugins {
    id("java")
    id("application")
    id("io.qameta.allure") version "2.12.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("io.rest-assured:rest-assured:5.5.0") {
        exclude(group = "commons-codec", module = "commons-codec")
    }
    testImplementation("io.rest-assured:json-path:5.5.0")
    implementation("commons-codec:commons-codec:1.17.1")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")

    implementation("io.qameta.allure:allure-rest-assured:2.29.0")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("allureReport")
}

allure {
    version.set("2.12.0")
}
