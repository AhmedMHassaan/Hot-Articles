package com.ahmedmhassaan.domain.repos

class FakeLanguageRepository : LanguageRepository {
    private var currentLanguage = ""
    override fun saveLanguage(language: String): Boolean {
        currentLanguage = language
        return true
    }

    override fun getSavedLanguage(): String {
        return currentLanguage
    }
}