package com.ahmedmhassaan.orangetask.presentation.base

import android.content.Context
import android.content.res.Configuration
import com.ahmedmhassaan.core.activity.CoreActivity
import com.ahmedmhassaan.data.local.database.LanguageSharedPreferences
import com.ahmedmhassaan.domain.usecase.GetSavedLanguageUseCase
import com.ahmedmhassaan.orangetask.utils.LocaleContextWrapper
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
open class LocatableActivity @Inject constructor(
) : CoreActivity() {


    override fun attachBaseContext(base: Context) {
//        super.attachBaseContext(RuntimeLocaleChanger.wrapContext(base))

        val language = LanguageSharedPreferences(base).getLanguage()
        super.attachBaseContext(LocaleContextWrapper.wrap(base, language));
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
//        RuntimeLocaleChanger.overrideLocale(this)


    }
}