import java.io.ByteArrayOutputStream

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
}

val kotlinVersion = "1.5.0"
val springBootVersion = "2.5.0"

// find the last commit
fun getGitHashLastCommit(): String {
	val stdout = ByteArrayOutputStream()
	exec {
		commandLine("git", "rev-parse", "HEAD")
		standardOutput = stdout
	}

	return stdout.toString().trim()
}

springBoot {
	buildInfo {
		properties {
			additional["commitId"] = getGitHashLastCommit()
			additional["springBootVersion"] = springBootVersion
			additional["kotlinVersion"] = kotlinVersion
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// SWAGGER - FRAMEWORK
	implementation("io.springfox:springfox-boot-starter:3.0.0")

	// SPRING FRAMEWORK AND CORE
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// Development Runtime
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("com.integralblue:log4jdbc-spring-boot-starter:1.0.2")

	// DB Driver
	runtimeOnly("org.postgresql:postgresql")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
