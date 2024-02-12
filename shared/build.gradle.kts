import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqlDelight)
}

kotlin {

    js {
        moduleName = "shared"
        browser {
            commonWebpackConfig {
                outputFileName = "shared.js"
            }
        }
        binaries.executable()
    }

//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        moduleName = "shared"
//        browser {
//            commonWebpackConfig {
//                outputFileName = "shared.js"
//            }
//        }
//        binaries.executable()
//    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm("desktop") {
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
//            export("dev.icerock.moko:mvvm-flow:0.16.1")
//            export("dev.icerock.moko:mvvm-state:0.16.1")
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "MultiPlatformLibrary"
            isStatic = true
            export("dev.icerock.moko:mvvm-core:0.16.1")
//            export("dev.icerock.moko:mvvm-flow:0.16.1")
//            export("dev.icerock.moko:mvvm-state:0.16.1")

//            extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
//            extraSpecAttributes["exclude_files"] = "['src/commonMain/resources/**', 'src/iosMain/resources/MR/**']"
        }
    }

    sourceSets {

        val desktopMain by getting

        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            //bundle for koin
            api(libs.bundles.koin)
            //bundle for ktor
            api(libs.bundles.ktor)
            //bundle for moko
            api(libs.bundles.moko)
            //voyager
            implementation(libs.bundles.voyager)

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

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.ktor.cio)
            implementation(libs.kmp.sqldelight.jvm)
            implementation(libs.kotlinx.coroutines.swing)
//            implementation("ch.qos.logback:logback-classic:1.2.3")
            implementation("org.slf4j:slf4j-log4j12:1.7.29")

        }

        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(compose.html.svg)
            implementation(libs.ktor.js)
            implementation(libs.kmp.sqldelight.web)
            implementation(compose.runtime)
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
            implementation(npm("@cashapp/sqldelight-sqljs-worker", "2.0.1"))
            implementation(npm("sql.js", "1.8.0"))

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
//    sourceSets["main"].resources.srcDirs("src/commonMain/composeResources")
//    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/composeResources")

}
//Donot change the following...
val applicationDB = "ApplicationDB"

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.multiplatform.kmmcc"
            packageVersion = "1.0.0"
//            windows {
//                iconFile.set(project.file("src/commonMain/composeResources/launcher_jvm.webp"))
//            }
//            macOS {
//                iconFile.set(project.file("src/commonMain/composeResources/launcher_jvm.webp"))
//            }
        }
    }
}

compose.experimental {
    web.application {}
}

sqldelight {
    databases {
        create(applicationDB) {
            packageName = "com.multiplatform.kmmcc.database"
            srcDirs.setFrom("src/commonMain/sqldelight")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/databases"))
            version = 1
            generateAsync = true
        }
    }
}