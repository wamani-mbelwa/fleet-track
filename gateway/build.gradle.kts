plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":platform"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:4.1.4")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
