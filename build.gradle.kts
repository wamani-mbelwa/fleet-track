plugins {
    id("java")
    id("org.springframework.boot") version "3.3.4" apply false
    id("io.spring.dependency-management") version "1.1.6"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    group = "com.fleetpay"
    version = "0.1.0"

    tasks.withType<Test> {
        useJUnitPlatform()
        systemProperty("spring.profiles.active", "test")
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
        testImplementation("org.assertj:assertj-core:3.25.3")
        testImplementation("org.awaitility:awaitility:4.2.1")
        testImplementation("org.testcontainers:junit-jupiter:1.20.1")
    }
}
