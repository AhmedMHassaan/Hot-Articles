import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")

}




android {
    namespace = "com.ahmedmhassaan.orangetask"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.ahmedmhassaan.orangetask"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


//        buildConfigField("String", "API_KEY",API_KEY)


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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        dataBinding = true
        viewBinding = true

    }

    kapt {
        correctErrorTypes = true
    }


}

dependencies {

//    implementation(project(path = ":domain", configuration = "default"))
//    implementation(project(path = ":data", configuration = "default"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":core"))

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    val nav_version = "2.7.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version") // nav component
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    implementation("com.github.GrenderG:Toasty:1.5.2") // Toasty for custom toasts
    implementation("com.github.bumptech.glide:glide:4.15.1") // Glide For Loading Images
    implementation("com.intuit.sdp:sdp-android:1.1.0") // SDP For Dimensions And Distinct
    implementation("com.intuit.ssp:ssp-android:1.1.0")  // SSP For Size

    implementation("com.google.dagger:hilt-android:2.47") // for hilt di
    kapt("com.google.dagger:hilt-compiler:2.47") // hilt di

    implementation("com.github.sharish:ShimmerRecyclerView:v1.3") // shimmer recView
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0") // refresh and CircularDrawable


    val paging_version = "3.2.0"
    implementation("androidx.paging:paging-runtime-ktx:$paging_version")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

}