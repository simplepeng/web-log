plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "simple.library.weblog"
    compileSdk = 35

    defaultConfig {
        minSdk = 16
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

//noinspection UseTomlInstead
dependencies {
//    implementation("org.java-websocket:Java-WebSocket:1.6.0")
//    implementation("com.nanohttpd:nanohttpd-webserver:2.2.0")
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    //
    implementation("org.nanohttpd:nanohttpd-websocket:2.3.1")
    api(project(":library-base"))
}