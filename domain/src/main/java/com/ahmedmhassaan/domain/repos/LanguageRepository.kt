package com.ahmedmhassaan.domain.repos

interface LanguageRepository {

    fun saveLanguage(language: String): Boolean

    fun getSavedLanguage(): String
}