plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}
group = "net.dambakk.ekkokammer"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
dependencies {
    implementation(project(":common"))
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")

    implementation("androidx.compose.ui:ui:1.0.0-alpha03")
    implementation("androidx.ui:ui-tooling:1.0.0-alpha03")
    implementation("androidx.compose.foundation:foundation:1.0.0-alpha03")
    implementation("androidx.compose.material:material:1.0.0-alpha03")
    implementation("androidx.compose.material:material-icons-core:1.0.0-alpha03")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-alpha03")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-alpha03")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.0.0-alpha03")
    androidTestImplementation("androidx.ui:ui-test:1.0.0-alpha03")
}
android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "net.dambakk.ekkokammer.android"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-alpha03"
    }
}
