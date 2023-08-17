package com.ahmedmhassaan.data.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.ahmedmhassaan.data.local.database.LanguageSharedPreferences
import com.ahmedmhassaan.data.local.datasources.LanguageDataSource
import com.ahmedmhassaan.domain.repos.LanguageRepository
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class LanguageRepositoryImplTest {

    private lateinit var languageRepository: LanguageRepository

    //    private lateinit var dataSource: LanguageDataSource
//    private lateinit var langPref: LanguageSharedPreferences
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Context?>().applicationContext
        languageRepository = LanguageRepositoryImpl(
            LanguageDataSource(
                LanguageSharedPreferences(context)
            )
        )
    }


    @Test
    fun testLanguageBeforeSavingNewOne_returnEmptyString() {
        val lang = languageRepository.getSavedLanguage()
        assertThat(lang).isEmpty()
    }

    @Test
    fun test_saveLanguage() {
        val expectedLang = "abcd"
        languageRepository.saveLanguage(expectedLang)
        val lang = languageRepository.getSavedLanguage()
        assertThat(lang).isEqualTo(expectedLang)
    }

}