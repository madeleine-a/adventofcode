plugins {
    kotlin("jvm") version "2.2.20"
}

group = "se.aoc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
        testImplementation(platform("org.junit:junit-bom:6.0.1"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()


}
kotlin {
    jvmToolchain(21)
}