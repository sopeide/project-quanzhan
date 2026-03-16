plugins {
    alias(libs.plugins.android.application)
}


android {
    namespace = "com.example.sensordemo"
    compileSdk = 35

    signingConfigs {
        getByName("debug") {
            storeFile = file("C:/Users/xiaoy/.android/debug.keystore")
            storePassword = "123456"
            keyAlias = "androiddebugkey"
            keyPassword = "123456"
        }
    }




    defaultConfig {
        applicationId = "com.example.sensordemo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}


//repositories {
//    flatDir {
//        dirs 'libs' // 指定本地库目录
//    }
//}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("org.java-websocket:Java-WebSocket:1.5.3")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")


    // 高德地图定位
    implementation("com.amap.api:3dmap:9.8.3") // 最新稳定版本地图 SDK
//    implementation("com.amap.api:location:6.1.0") // 位置 SDK（你已加）
//    implementation(name: "AMap3DMap_10.1.300_AMapSearch_9.7.4_AMapLocation_6.4.9_20250509", ext: "aar")

}