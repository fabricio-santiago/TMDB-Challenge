import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.santiago.fabricio.tmdbchallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.santiago.fabricio.tmdbchallenge"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            buildConfigField(
                "String",
                "API_KEY",
                "\"${gradleLocalProperties(rootDir, providers).getProperty("api_key")}\""
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField(
                "String",
                "API_KEY",
                "\"${gradleLocalProperties(rootDir, providers).getProperty("api_key")}\""
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Default Dependencys
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.core.ktx)
    implementation(libs.androidx.runner)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Coil
    implementation(libs.coil.compose)

    //Timber
    implementation(libs.timber)

    //DataStore
    implementation(libs.androidx.datastore.preferences)

    //Splashscreen
    implementation(libs.androidx.core.splashscreen)

    //Gson
    implementation(libs.gson)

    //Other - Compose dependencies
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.androidx.navigation.compose)

    // Paging3
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)

    // Coroutines
    implementation(libs.jetbrains.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v261)
    implementation(libs.jetbrains.kotlinx.coroutines.core)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //DI - Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt (libs.hilt.compiler)
    kapt (libs.androidx.hilt.compiler)

    //Room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    kapt("androidx.room:room-compiler:2.6.1")

    //Testes
    //Hilt
    kaptAndroidTest (libs.hilt.android.compiler)
    androidTestImplementation (libs.hilt.android.testing)

    // Unit tests
    testImplementation (libs.junit)
    testImplementation (libs.androidx.core.testing)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.mockito.kotlin)
    testImplementation (libs.mockito.inline)
    testImplementation (libs.androidx.room.testing)
    testImplementation (libs.mockk)
    testImplementation (libs.cash.turbine)
    testImplementation (libs.paging.common)

    // Instrumentation tests
    androidTestImplementation (libs.androidx.core.testing)
    androidTestImplementation (libs.kotlinx.coroutines.test)
    androidTestImplementation (libs.truth)
    testImplementation (libs.truth.java8.extension)
    androidTestImplementation (libs.androidx.junit.v115)
    androidTestImplementation (libs.androidx.espresso.core.v351)

    androidTestImplementation (libs.ui.test.junit4)
    debugImplementation (libs.ui.tooling)
    debugImplementation (libs.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}