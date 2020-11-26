buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        val kotlinVersion = "1.4.10"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:4.2.0-alpha16")
        classpath("org.jetbrains.kotlin:kotlin-android-extensions:$kotlinVersion")

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0")

        classpath("org.koin:koin-gradle-plugin:2.2.0")

    }
}
group = "net.dambakk.ekkokammer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
