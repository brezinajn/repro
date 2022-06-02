plugins {
    kotlin("multiplatform") version "1.6.21"
    application
    id("com.google.devtools.ksp") version "1.6.21-1.0.5"
}

group = "com.github.brezinajn"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

val arrowVersion = "1.1.2"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
    }
    sourceSets {
        dependencies {
            ksp("io.arrow-kt:arrow-optics-ksp-plugin:$arrowVersion")
        }
        val commonMain by getting{
            dependencies {
                implementation(project.dependencies.platform("io.arrow-kt:arrow-stack:$arrowVersion"))
                implementation("io.arrow-kt:arrow-core")
                implementation("io.arrow-kt:arrow-optics")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(project.dependencies.platform("io.arrow-kt:arrow-stack:$arrowVersion"))
                implementation("io.arrow-kt:arrow-core")
                implementation("io.arrow-kt:arrow-optics")
            }
        }

    }
}

application {
    mainClass.set("com.github.brezinajn.repro.ApplicationKt")
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}