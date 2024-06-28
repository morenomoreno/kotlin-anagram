plugins {
    kotlin("jvm") version "1.8.20"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.1")
    testImplementation("org.mockito:mockito-core:3.9.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
    testImplementation("com.github.stefanbirkner:system-lambda:1.2.0")
}

application {
    mainClass.set("com.morenomoreno.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaExec> {
    standardInput = System.`in`
}