buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "${Versions.kotlin}"
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
        mavenLocal()
    }
}