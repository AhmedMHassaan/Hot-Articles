package com.ahmedmhassaan.data.repository

import com.ahmedmhassaan.data.local.datasources.LanguageDataSource
import com.ahmedmhassaan.domain.repos.LanguageRepository
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(
    private val languageDataSource: LanguageDataSource
) : LanguageRepository {
    override fun saveLanguage(language: String): Boolean {
        return languageDataSource.saveLanguage(language)
    }

    override fun getSavedLanguage(): String {
        return languageDataSource.getLanguage()
    }
}