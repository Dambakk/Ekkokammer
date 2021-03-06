plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs.kotlin")
    id("koin")
}
group = "net.dambakk.ekkokammer"
version = "1.0-SNAPSHOT"

val composeVersion = "1.0.0-alpha07"
val accompanistVersion = "0.3.3.1"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
dependencies {
    implementation(project(":common"))
    implementation("com.google.android.material:material:1.2.1")

    // JetBrains
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC")

    // Compose:
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-core:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("androidx.compose.runtime:runtime-rxjava2:$composeVersion")
    implementation("androidx.navigation:navigation-compose:1.0.0-alpha02")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("dev.chrisbanes.accompanist:accompanist-coil:$accompanistVersion")
    implementation("dev.chrisbanes.accompanist:accompanist-insets:$accompanistVersion")


    // Androidx
    val navigationVersion = "2.3.0-alpha01" // TestNavHostController available in alpha
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.2.0")
    implementation("androidx.fragment:fragment-ktx:1.3.0-beta01")
    implementation("androidx.activity:activity-ktx:1.2.0-beta01")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")


    val koin_version = "2.2.0"
    implementation("org.koin:koin-androidx-scope:$koin_version")
    implementation("org.koin:koin-androidx-viewmodel:$koin_version")
    implementation("org.koin:koin-androidx-fragment:$koin_version")
    implementation("org.koin:koin-androidx-ext:$koin_version")

    implementation("io.ktor:ktor-client-cio:1.4.0")

    implementation("com.prof.rssparser:rssparser:2.1.1")
    implementation("com.soywiz.korlibs.klock:klock-android:2.0.0")


    androidTestImplementation("androidx.ui:ui-test:$composeVersion")
}
android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "net.dambakk.ekkokammer.android"
        minSdkVersion(24)
        targetSdkVersion(30)
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
//        freeCompilerArgs = listOf(
//            "-XXLanguage:+NonParenthesizedAnnotationsOnFunctionalTypes"
//        )
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "$composeVersion"
    }
}
