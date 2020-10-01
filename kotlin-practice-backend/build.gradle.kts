import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val springBoot = id("org.springframework.boot").version("2.3.3.RELEASE")
    val springDependencyManagement = id("io.spring.dependency-management").version("1.0.10.RELEASE")
    kotlin("jvm") version "1.4.0"
    kotlin("plugin.spring") version "1.4.0"
}

group = "com.shenjies88.practice"
version = ""
java.sourceCompatibility = JavaVersion.VERSION_14

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    //Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    //Databases
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.3")
    runtimeOnly("com.h2database:h2")
    //Swagger
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("com.github.xiaoymin:swagger-bootstrap-ui:1.9.6")
    //Other
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "14"
    }
}
