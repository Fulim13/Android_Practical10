plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // Firebase Firestore (NOT USING)
    // id("com.google.gms.google-services") version "4.4.1"

    // KSP (Kotlin Symbol Processing) --> https://github.com/google/ksp/releases
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
}

android {
    namespace = "com.example.demo"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.demo"
        minSdk = 30
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    // TODO: Add settings -> send email
    packaging {
        resources {
            excludes.add("/META-INF/NOTICE.md")
            excludes.add("/META-INF/LICENSE.md")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.0")

    // Firebase Firestore (NOT USING)
    implementation("com.google.firebase:firebase-firestore:24.11.0")

    // Room database libraries
    implementation("androidx.room:room-ktx:2.5.0")
    ksp("androidx.room:room-compiler:2.5.0")

    // TODO: Add dependency -> send email
    implementation("com.sun.mail:android-mail:1.6.7")
    implementation("com.sun.mail:android-activation:1.6.7")

    // TODO: Add dependency -> qr code
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
}