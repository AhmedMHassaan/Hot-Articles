package com.ahmedmhassaan.orangetask.utils

import android.content.Context
import android.content.res.Configuration
import com.ahmedmhassaan.orangetask.presentation.base.LocatableActivity
import java.util.Locale

object RuntimeLocaleChanger {
    fun wrapContext(context: Context): Context {

        val savedLocale =
            createLocaleFromSavedLanguage() // load the user picked language from persistence (e.g SharedPreferences)
                ?: return context // else return the original untouched context

        // as part of creating a new context that contains the new locale we also need to override the default locale.
        Locale.setDefault(savedLocale)

        // create new configuration with the saved locale
        val newConfig = Configuration()
        newConfig.setLocale(savedLocale)

        return context.createConfigurationContext(newConfig)
    }


    private fun createLocaleFromSavedLanguage(): Locale? {
        if (true)
            return Locale("ar", "eg")
        else
            return Locale.getDefault()

    }

    fun overrideLocale(context: Context) {

        val savedLocale =
            createLocaleFromSavedLanguage() // load the user picked language from persistence (e.g SharedPreferences)
                ?: return // nothing to do in this case

        // as part of creating a new context that contains the new locale we also need to override the default locale.
        Locale.setDefault(savedLocale)

        // create new configuration with the saved locale
        val newConfig = Configuration()
        newConfig.setLocale(savedLocale)

        // override the locale on the given context (Activity, Fragment, etc...)
        context.resources.updateConfiguration(newConfig, context.resources.displayMetrics)

        // override the locale on the application context
        if (context != context.applicationContext) {
            context.applicationContext.resources.run {
                updateConfiguration(
                    newConfig,
                    displayMetrics
                )
            }
        }

    }
}