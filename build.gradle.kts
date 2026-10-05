buildscript {
    dependencies {
        // Upgrade AGP 9.4's built-in Kotlin (2.2.10) to 2.4.10 so it can read
        // metadata from libraries compiled with Kotlin 2.4 (Compose 2026.09, Coil 3.6).
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.4.10")
    }
}

plugins {
    id("com.android.application") version "9.4.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.4.10" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.4.10" apply false
    id("com.google.gms.google-services") version "4.5.0" apply false
}
