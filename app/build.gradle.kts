plugins {
    id("com.android.application")
    kotlin("kapt")
    kotlin("android")
}

android {
    namespace = "com.skyanzhin.todostorage"
    compileSdk = 33

    defaultConfig {
        applicationId  = "com.skyanzhin.todostorage"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(androidx.corektx)
    implementation(androidx.appcompat)
    implementation(androidx.material)
    implementation(compose.ui)
    implementation(compose.material)
    implementation(compose.icons)
    implementation(compose.uitoolingpreview)
    implementation(androidx.lifecycleruntimektx)
    implementation(compose.activity)
    implementation(compose.navigation)
    implementation(compose.lifecycleruntime)
    implementation(compose.lifecycleviewmodel)

    implementation(libs.hilt)
    implementation(libs.hiltnavigation)
    kapt(libs.hiltcompiler)

    implementation(libs.room)
    implementation(libs.roomktx)
    kapt(libs.roomcompiler)

    testImplementation(libs.junit)
    androidTestImplementation(androidx.junit)
    androidTestImplementation(androidx.espresso)
    androidTestImplementation(compose.junit)
    debugImplementation(compose.uitooling)
}
