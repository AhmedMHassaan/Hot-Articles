package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.repos.FakeLanguageRepository
import com.ahmedmhassaan.domain.repos.LanguageRepository
import com.google.common.truth.Truth
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class GetSavedLanguageUseCaseTest {

    private lateinit var getSavedLanguageUseCase: GetSavedLanguageUseCase
    private lateinit var repository: LanguageRepository

    @Before
    fun setUp() {
        repository = FakeLanguageRepository()
        getSavedLanguageUseCase = GetSavedLanguageUseCase(repository)
    }


    @Test
    fun `test that repository fetched saved language`() {
        val currentLanguage = "ar"
        repository.saveLanguage(currentLanguage)
        val savedLanguage = getSavedLanguageUseCase.execute()
        Truth.assertThat(savedLanguage).isEqualTo(currentLanguage)
    }   
}