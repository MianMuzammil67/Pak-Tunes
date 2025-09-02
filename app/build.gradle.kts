plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.navigation.safe.args)
    alias(libs.plugins.google.gms.google.services)
    id("kotlin-parcelize")

}
android {
    namespace = "com.example.paktunes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.paktunes"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.play.services.cast.framework)
    implementation(libs.androidx.lifecycle.service)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.recyclerview)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //    Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //    Glide
    implementation(libs.glide)


    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.androidx.lifecycle.runtime.ktx)


    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.common)
    implementation (libs.androidx.media3.session)


    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    implementation (libs.androidx.lifecycle.livedata.ktx)
}