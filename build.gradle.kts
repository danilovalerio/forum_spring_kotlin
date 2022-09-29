import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
/**
tasks.register("bootRunDev") {
	group = "br.com.danilo.exemplo"
	doFirst {
		tasks.bootRun.configure{
			systemProperty("spring.profiles.active","dev")
		}
	}
	finalizedBy("bootRun")
}*/

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.noarg") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

noArg {
	annotation("NoArg")
	invokeInitializers = false
}

group = "br.com.danilo.exemplo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	//JPA
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	//Database
	//https://mvnrepository.com/artifact/org.postgresql/postgresql
	implementation("org.postgresql:postgresql:42.5.0")
	//implementation("com.h2database:h2")
	//Flyway
	implementation("org.flywaydb:flyway-core")
	//Cache para informacoes que n�o mudam com frequencia em memoria somente em debug, para prod precisa de ferramenta
	implementation("org.springframework.boot:spring-boot-starter-cache")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security
	implementation("org.springframework.boot:spring-boot-starter-security")
	// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

/*
flyway {
	url = "jdbc:postgresql://localhost:5432/forum"
	user = "postgres"
	password = "danilo"
	locations = arrayOf("classpath:db/migration")
}*/

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
