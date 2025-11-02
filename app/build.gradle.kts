plugins {
    alias(libs.plugins.agp.app)
    alias(libs.plugins.kotlin)
}

android {
    namespace = "com.diskree.noanrandcrashdialogs"
    compileSdk = 33

    signingConfigs {
        create("release") {
            storeFile = file(System.getenv("APP_RELEASE_STORE_FILE") ?: project.property("APP_RELEASE_STORE_FILE") as String)
            storePassword = System.getenv("APP_RELEASE_STORE_PASSWORD") ?: project.property("APP_RELEASE_STORE_PASSWORD") as String
            keyAlias = System.getenv("APP_RELEASE_KEY_ALIAS") ?: project.property("APP_RELEASE_KEY_ALIAS") as String
            keyPassword = System.getenv("APP_RELEASE_KEY_PASSWORD") ?: project.property("APP_RELEASE_KEY_PASSWORD") as String
        }
    }

    defaultConfig {
        minSdk = 33
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {}
    }

    kotlin {
        jvmToolchain(17)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources {
            merges += "META-INF/xposed/*"
            excludes += "**"
        }
    }

    lint {
        abortOnError = true
        checkReleaseBuilds = false
    }
}

dependencies {
    implementation(libs.libxposed.api)
    implementation(libs.libxposed.service)
    implementation(libs.libxposed.interface)
}
