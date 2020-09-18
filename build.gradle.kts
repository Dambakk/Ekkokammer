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
        classpath("com.android.tools.build:gradle:4.2.0-alpha11")
        classpath("org.jetbrains.kotlin:kotlin-android-extensions:$kotlinVersion")

        val nav_version = "2.3.0"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")

    }
}
group = "net.dambakk.ekkokammer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
