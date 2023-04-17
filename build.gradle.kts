import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                //material
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.material3)
                //postgresSQL
                implementation("org.postgresql:postgresql:42.5.0")
                //exposed orm
                implementation("org.jetbrains.exposed:exposed-core:0.41.1")
                implementation("org.jetbrains.exposed:exposed-dao:0.41.1")
                implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")
                implementation("org.slf4j:slf4j-simple:2.0.3") //what is this?
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "FInalProjectM6"
            packageVersion = "1.0.0"
        }
    }
}
