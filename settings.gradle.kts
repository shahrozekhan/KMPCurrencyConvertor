enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
//        maven {
//            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
//        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
//        maven {
//            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
//        }
    }
}

rootProject.name = "KMMCC"
include(":androidApp")
include(":shared")