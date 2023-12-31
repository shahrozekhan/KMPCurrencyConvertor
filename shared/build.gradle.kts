
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:mvvm-core:0.16.1")
            export("dev.icerock.moko:mvvm-flow:0.16.1")
            export("dev.icerock.moko:mvvm-state:0.16.1")
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "MultiPlatformLibrary"
            isStatic = true
            export("dev.icerock.moko:mvvm-core:0.16.1")
            export("dev.icerock.moko:mvvm-flow:0.16.1")
            export("dev.icerock.moko:mvvm-state:0.16.1")
        }
    }

    sourceSets {

        commonMain.dependencies {
            //put your multiplatform dependencies here
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            //bundle for koin
            api(libs.bundles.koin)
            //bundle for ktor
            api(libs.bundles.ktor)
            //bundle for moko
            api(libs.bundles.moko)

            implementation(libs.kotlinx.date)
            implementation(libs.kotlin.bignum)
            // multiplatform sqldelight
            implementation(libs.kmp.sqldelight)
            implementation(libs.kotlinx.coroutines)

        }

        androidMain.dependencies {
            api(libs.androidx.coreKtx)
            //ktor https Engine for android
            implementation(libs.ktor.android)
            //dependency injection for android compose
            implementation(libs.bundles.android.koin)
            //Database
            implementation(libs.kmp.sqldelight.android)

        }
        iosMain.dependencies {
            //ktor https Engine for IOS
            implementation(libs.ktor.client.darwin)
            //Database
            implementation(libs.kmp.sqldelight.ios)

        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.multiplatform.kmmcc"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
//    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/resources")

}

sqldelight {
    databases{
        create("ExchangeRateDB"){
            packageName="com.multiplatform.kmmcc.database"

        }
    }
}