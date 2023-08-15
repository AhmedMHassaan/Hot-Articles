package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.repos.LanguageRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSavedLanguageUseCase @Inject constructor(
    private val languageRepository: LanguageRepository
) {

    fun execute(): String {
        return languageRepository.getSavedLanguage()
    }
}