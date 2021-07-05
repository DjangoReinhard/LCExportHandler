buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm") version Versions.kotlin
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}