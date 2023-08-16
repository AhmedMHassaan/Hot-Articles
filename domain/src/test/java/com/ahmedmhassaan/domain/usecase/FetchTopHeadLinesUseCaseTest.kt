package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.repos.ArticlesRepository
import com.ahmedmhassaan.domain.repos.FakeRemoteArticlesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
class FetchTopHeadLinesUseCaseTest {

    private lateinit var repository:ArticlesRepository

    private lateinit var useCase :FetchTopHeadLinesUseCase

    @Before
    fun setUp() {
        repository = FakeRemoteArticlesRepository()
        useCase = FetchTopHeadLinesUseCase(repository)
    }
}