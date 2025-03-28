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

    implementation("com.google.inject:guice:6.0.0") {
        exclude (group = "com.google.guava", module = "guava")
    }

    implementation("com.google.guava:guava:33.2.1-jre")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("org.slf4j:slf4j-api:2.0.16")
    testImplementation("ch.qos.logback:logback-classic:1.5.18")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("allureReport")
}

tasks.register("cleanAllureDir", Delete::class) {
    delete("build/reports/allure-report")
}

tasks.named("allureReport") {
    dependsOn("cleanAllureDir")
}