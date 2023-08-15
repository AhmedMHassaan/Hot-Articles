package com.ahmedmhassaan.data.local.database

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.intellij.lang.annotations.Language
import java.util.Locale
import javax.inject.Inject

class LanguageSharedPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sharedPreferences =
        context.getSharedPreferences("${context.packageName}.languages.pref", Context.MODE_PRIVATE)

    private val LANGUAGE_TAG = "language"


    fun saveLanguage(language: String): Boolean {
        return sharedPreferences.edit().putString(LANGUAGE_TAG, language).commit()
    }

    fun getLanguage() = sharedPreferences.getString(LANGUAGE_TAG, "") ?: ""
}