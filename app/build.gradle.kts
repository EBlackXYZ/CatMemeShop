
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp") version "2.3.4"
}

android {
    namespace = "com.example.catmemeshop"

    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.catmemeshop"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"

        // Obtener la API de Google Maps desde local.properties
        manifestPlaceholders["MAPS_API_KEY"] = run {
            val propiedades = Properties()
            val archivo = rootProject.file("local.properties")

            if (archivo.exists()) {
                archivo.inputStream().use {
                    propiedades.load(it)
                }
            }

            propiedades.getProperty("MAPS_API_KEY", "")
        }
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf(
                    "androidx.**",
                    "kotlin.**",
                    "kotlinx.**"
                )
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Pruebas
    testImplementation(libs.junit)

    androidTestImplementation(
        platform(libs.androidx.compose.bom)
    )

    androidTestImplementation(
        libs.androidx.compose.ui.test.junit4
    )

    androidTestImplementation(
        libs.androidx.espresso.core
    )

    androidTestImplementation(
        libs.androidx.junit
    )

    debugImplementation(
        libs.androidx.compose.ui.test.manifest
    )

    debugImplementation(
        libs.androidx.compose.ui.tooling
    )

    // Firebase Authentication
    implementation(
        platform("com.google.firebase:firebase-bom:34.5.0")
    )

    implementation(
        "com.google.firebase:firebase-auth"
    )

    // Iconos
    implementation(
        "androidx.compose.material:material-icons-extended"
    )

    // Google Maps
    implementation(
        "com.google.maps.android:maps-compose:6.12.0"
    )

    implementation(
        "com.google.android.gms:play-services-maps:19.2.0"
    )

    // Base de datos Room
    implementation(
        "androidx.room:room-runtime:2.8.4"
    )

    implementation(
        "androidx.room:room-ktx:2.8.4"
    )

    ksp(
        "androidx.room:room-compiler:2.8.4"
    )

    // ViewModel para MVVM
    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4"
    )
}
