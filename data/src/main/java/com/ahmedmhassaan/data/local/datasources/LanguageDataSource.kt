package com.ahmedmhassaan.data.local.datasources

import com.ahmedmhassaan.data.local.database.LanguageSharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageDataSource @Inject constructor(
    private val langSharedPref: LanguageSharedPreferences
) {

    fun saveLanguage(language: String): Boolean {
        return langSharedPref.saveLanguage(language)
    }

    fun getLanguage(): String {
        return langSharedPref.getLanguage()
    }
}