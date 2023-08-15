package com.ahmedmhassaan.orangetask.presentation

import android.app.Application
import android.content.Context
import com.ahmedmhassaan.orangetask.utils.RuntimeLocaleChanger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(RuntimeLocaleChanger.wrapContext(base))
    }

}