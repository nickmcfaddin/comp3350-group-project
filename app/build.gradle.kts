    plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.easyshopper"
    compileSdk = 34

    testOptions {
        unitTests.isReturnDefaultValues = true;
    }

    defaultConfig {
        applicationId = "com.example.easyshopper"
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    debugImplementation("androidx.fragment:fragment-testing:1.6.2");
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    implementation ("org.hsqldb:hsqldb:2.4.1")
    testImplementation("com.google.guava:guava:33.0.0-android")
    implementation("com.itextpdf:itext7-core:7.1.16")
}