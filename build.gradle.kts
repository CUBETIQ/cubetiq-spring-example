import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.3" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    kotlin("jvm") version "1.5.21" apply false
    kotlin("plugin.spring") version "1.5.21" apply false
    kotlin("plugin.jpa") version "1.5.21" apply false
}

allprojects {
    group = "com.cubetiqs"
    version = "0.0.1-SNAPSHOT"

    val javaVersion = "11"

    tasks.withType<JavaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = javaVersion
        }
    }

    repositories {
        maven("https://m.ctdn.net")
        maven("https://m.ctdn.net/snapshots")
    }
}

subprojects {
    apply {
        plugin("io.spring.dependency-management")
    }

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }
}